#!/bin/bash
APPS='
Activity-Asynchronous-Event-Ordering
Activity-Saved-State
Application-Modeling
ArrayCopy
ArraySlice
Button-Object-Allocation
Clinit
Clone
Dynamic-Dispatch
Event-Context-Shared-Pref-Listener
Event-Ordering
Fragments
ICC-Action-String-Operations
ICC-Broadcast-Programmatic-IntentFilter
ICC-Component-Not-in-Manifest
ICC-Concat-Action-String
ICC-Intent-Component-Name
ICC-Intent-Passed-Through-API
ICC-Non-Constant-Class-Object
ICC-Pass-Action-String-Through-API
ICC-Service-Messages
ICC-Unresolvable-Intent
Intent-Class-Modeling
Non-Sink-Argument-Flow
OutputStream
Parcel
Pattern-Matcher
Public-API-Field
Serialization
Service-Lifecycle
SharedPreferences
StringFormatter
String-to-Char
ToString
Two-Components-Share-Memory 
'

#APPS=$APPS_NON_ICC
MAKE_TEMPLATE=../test-doc/make.template
SCRIPTNAME=`basename $0`
SCRIPTDIR=`dirname $0` 
APPS=`cat $SCRIPTDIR/apps.txt`
SCRIPTDIR=`(cd $SCRIPTDIR && pwd)`
if [ "$#" -eq 0 ]; then
    echo "$SCRIPTNAME makefile | project | build | apk-setup | check-info | specdump | create-expected | gen-review | gen-droidbench | fix-manifest"
    exit -1
fi 

CMD=$1
for APP in $APPS; do
    #runds dump $APP;
    echo $APP
    app=`echo $APP | tr 'A-Z' 'a-z' | tr '-' '_' `
    PKG=edu.mit.$app
    case $CMD in
        makefile)
        (cd $APP; echo "NAME:= $APP" > Makefile; cat $MAKE_TEMPLATE >> Makefile);;

        create-expected)
        (cd $APP; grep FLOW droidsafe-gen/info-flow-results.txt >  expected-info-flows.txt);;

        gen-droidbench)
        (cd $APP; mkdir -p conf && cat expected-info-flows.txt ../test-doc/DroidBench.xml | sed "s/TEST_CASE/$APP/" | sed "s/APP/$app/" > conf/DroidBench.xml);;

        fix-manifest)
        (cd $APP; ../test-doc/manifest-package.py AndroidManifest.xml $PKG);;


        gen-review)
        echo "######################"
	    echo "STATUS:   OFFICIAL:   POS:   NEG:   MISSED:"
        echo $APP/expected-info-flows.txt
        echo $APP/droidsafe-gen/info-flow-results.txt
        echo " "
        ;;

        project)
            (cd $APP; rm -f build.xml; make update);;

        build)
            (cd $APP; make build);;

        release)
            (cd $APP; ant release);;

        apk-setup)
            (mkdir -p apk/$APP; cd apk/$APP; ln -sf ../../$APP/expected-info-flows.txt; ln -sf ../../$APP/bin/$APP-debug.apk $APP.apk;
             ln -sf ../../$APP/Makefile . ; ln -sf ../../$APP/build.xml .) ;;

        apk-remove)
            (cd $APP; git rm $APP.apk);;
        *)
           echo "invalid $CMD "
           exit -1 
    esac
done
