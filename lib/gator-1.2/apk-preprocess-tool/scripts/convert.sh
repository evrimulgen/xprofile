##!/bin/bash
#
# convert apk to eclipse-liked project 

# Cleanup created apk.
rm_createAPK ()
{
  if [ "$oldName" != "$name" ]; then
    rm "$apkPath"
  fi
}

# the directory of the dex2jar.
dex2jarDir="dex2jar-0.0.9.15"

#test if the parameter is given.
if test $# -lt 1; then
  echo "lack the path of the apk" >&2
  exit 1
fi

#the path from the parameter.
oldApkPath="$1"

#get the sdk version from the apk.
str=$(aapt list -a "${oldApkPath}" | grep "targetSdkVersion")

if [[ $str == "" ]]; then
  str=$(aapt list -a "${oldApkPath}" | grep "maxSdkVersion")
fi

if [[ $str == "" ]]; then
  str=$(aapt list -a "${oldApkPath}" | grep "minSdkVersion")
fi

i=$((${#str}-4))
SDKVersion=${str:$i:4}

if [[ $SDKVersion == \)* ]]; then
  i=$((${#str}-3))
SDKtargetLevel="${str:$i:3}"
else
  SDKtargetLevel=$SDKVersion
fi
  targetLevel=$((SDKtargetLevel))

#the directory of the apk.
apkDir=$(dirname "$oldApkPath")
#the name of the apk.
oldName=$(basename "$oldApkPath" ".apk")
#the name of the apk without space.
name=${oldName//[[:blank:]]/}
#deal with two apps with wrong sdk level.
if [[ $name == "KeePassDroid" || $name == "FBReader" ]]; then
  targetLevel=14
fi
#deal with apps with sdk level 4, they actually use 8.
if [[ $targetLevel == 4 ]];then
  targetLevel=8
fi

#the name of the apk should not have space, since it may cause some errors. Here we deal with this case.
if [ "$oldName" != "$name" ]; then
  cp "$oldApkPath" "$apkDir/${name}.apk"
  apkPath=$apkDir/${name}.apk
else
  apkPath=$oldApkPath
fi
  targetDir="${name}forEclipseProject"
# test where we are, in the script directory or in the gator-apk directory.
addtionalToolsDir="scripts"
if [ -e apktool_2.0.0b9.jar ];then
  cd ..
fi
# if output does not exist, create one.
if [ ! -e ./tmp/output ];then
  mkdir ./tmp/output
fi
# go to the output directory.
cd ./tmp/output
# run the two tools and form the output.
if ../../lib/gator-1.2/apk-preprocess-tool/$addtionalToolsDir/$dex2jarDir/d2j-dex2jar.sh ../../$apkPath && java -jar ../../lib/gator-1.2/apk-preprocess-tool/$addtionalToolsDir/apktool_2.0.0b9.jar d ../../$apkPath ; then

  mkdir "$targetDir"
  echo target=android-$targetLevel > $targetDir/project.properties
  mv $name/AndroidManifest.xml $targetDir
  mv $name/res $targetDir
  rm -r $name
  mkdir -p $targetDir/bin/classes
  mv ${name}-dex2jar.jar $targetDir/bin/classes
  cd $targetDir/bin/classes
  jar -xf ${name}-dex2jar.jar
  rm -r android
  rm_createAPK
else
  rm_createAPK
  exit 1
fi


