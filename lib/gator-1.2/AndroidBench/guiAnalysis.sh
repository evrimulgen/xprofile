#!/bin/bash

# guiAnalysis.sh - part of the GATOR project
#
# Copyright (c) 2014, The Ohio State University
#
# This file is distributed under the terms described in LICENSE in the root
# directory.

# Purpose: to run analysis on the programs used in our CGO'14 paper.

# This is the root directory of GATOR. You need to change its value to wherever
# you place GATOR.
if [[ "${GatorRoot}" == "" ]]; then
  GatorRoot=/Users/musgravejw/Development/school/gator-1.2
fi

# This is the root directory of AndroidBench.
AB=$GatorRoot/AndroidBench

# This is the directory of SDK
if [[ "${ADK}" == "" ]]; then
  ADK="${HOME}/Development/android-sdk-macosx"
fi

# Command template to invoke the analysis. Here, ADK refers to the root
# directory of the Android SDK. You need to setup this environment variable, or
# you can modify the following line to reflect your SDK installation location.
CMD="$GatorRoot/SootAndroid/scripts/guiAnalysis.sh $AB $ADK"

# For each program, build its own specific arguments to fill in the command
# template, and construct the complete command for invocation.

run-apv() {
  echo "=== START analyzing APV"
  pushd $AB/apv
  if  test ! -d pdfview ; then
    unzip apv-0.4.0.zip
  fi
  $CMD pdfview android-10 APV 2>&1 | tee log.apv
  popd
  echo "=== END analyzing APV; result saved to $AB/apv/log.apv"
}


run-astrid() {
  echo "=== START analyzing Astrid"
  pushd $AB/astrid
  if test ! -d astrid ; then
    unzip astrid-4.6.3.zip
  fi
  $CMD astrid/astrid google-14 Astrid 2>&1 | tee log.astrid
  popd
  echo "=== END analyzing Astrid; result saved to $AB/astrid/log.astrid"
}

run-connectbot() {
  echo "=== START analyzing ConnectBot"
  pushd $AB/ConnectBot
  if  test ! -d connectbot ; then
    unzip ConnectBot-c6cc322.zip
  fi
  $CMD connectbot android-15 ConnectBot 2>&1 | tee log.connectbot
  popd
  echo "=== END analyzing ConnectBot; result saved to $AB/ConnectBot/log.connectbot"
}

run-fbreader() {
  echo "=== START analyzing FBReader"
  pushd $AB/FBReader
  if test ! -d FBReaderJ ; then
    unzip fbreader-1.7.8.zip
  fi
  $CMD FBReaderJ android-14 FBReader 2>&1 | tee log.fbreader
  popd
  echo "=== END analyzing FBReader; result saved to $AB/FBReader/log.fbreader"
}

run-keepassdroid() {
  echo "=== START analyzing KeePassDroid"
  pushd $AB/KeePassDroid
  if  test ! -d keepassdroid ; then
    unzip KeePassDroid-v1.99.6.zip
  fi
  $CMD keepassdroid android-14 KeePassDroid 2>&1 | tee log.keepassdroid
  popd
  echo "=== END analyzing KeePassDroid; result saved to $AB/KeePassDroid/log.keepassdroid"
}

run-k9() {
  echo "=== START analyzing K9"
  pushd $AB/k9
  if  test ! -d k-9 ; then
    unzip k9-4.330.zip
  fi
  $CMD k-9 android-15 K9 2>&1 | tee log.k9
  popd
  echo "=== END analyzing K9; result saved to $AB/k9/log.k9"
}

run-vlc() {
  echo "=== START analyzing VLC"
  pushd $AB/VLC
  if test ! -d vlc-android ; then
    unzip vlc-0.0.11.zip
  fi
  $CMD vlc-android/vlc-android android-17 VLC 2>&1 | tee log.vlc
  popd
  echo "=== END analyzing VLC; result saved to $AB/VLC/log.vlc"
}


run-vudroid() {
  echo "=== START analyzing VuDroid"
  pushd $AB/VuDroid
  if  test ! -d vudroid-1.4 ; then
    unzip vudroid-1.4.zip
  fi
  $CMD vudroid-1.4 android-7 VuDroid 2>&1 | tee log.vudroid
  popd
  echo "=== END analyzing VuDroid; result saved to $AB/VuDroid/log.vudroid"
}

run-tippytipper() {
  echo "=== START analyzing TippyTipper"
  pushd $AB/TippyTipper
  if  test ! -d tippytipper-1.2 ; then
    unzip tippytipper-1.2.zip
  fi
  $CMD tippytipper-1.2 android-8 TippyTipper 2>&1 | tee log.tippytipper
  popd
  echo "=== END analyzing TippyTipper; result saved to $AB/TippyTipper/log.tippytipper"
}

run-openmanager() {
  echo "=== START analyzing OpenManager"
  pushd $AB/OpenManager
  if  test ! -d Android-File-Manager-master ; then
    unzip OpenManager-51fece5.zip
  fi
  $CMD Android-File-Manager-master android-8 OpenManager 2>&1 | tee log.openmanager
  popd
  echo "=== END analyzing OpenManager; result saved to $AB/OpenManager/log.openmanager"
}

run-notepad() {
  echo "=== START analyzing NotePad"
  pushd $AB/NotePad
  if  test ! -d notepad-master ; then
    unzip notepad-5b0fa10.zip
  fi
  $CMD notepad-master/NotePad android-15 NotePad 2>&1 | tee log.notepad
  popd
  echo "=== END analyzing NotePad; result saved to $AB/NotePad/log.notepad"
}

run-mytracks() {
  echo "=== START analyzing MyTracks"
  pushd $AB/MyTracks
  if  test ! -d mytracks-v2.0.4 ; then
    unzip mytracks-v2.0.4.zip
  fi
  $CMD mytracks-v2.0.4/MyTracks android-17 MyTracks 2>&1 | tee log.mytracks
  popd
  echo "=== END analyzing MyTracks; result saved to $AB/MyTracks/log.mytracks"
}

run-barcodescanner() {
  echo "=== START analyzing BarcodeScanner"
  pushd $AB/BarcodeScanner
  if  test ! -d BarcodeScanner-4.4 ; then
    unzip BarcodeScanner-4.4.zip
  fi
  $CMD BarcodeScanner-4.4/android android-17 BarcodeScanner 2>&1 | tee log.barcodescanner
  popd
  echo "=== END analyzing BarcodeScanner; result saved to $AB/BarcodeScanner/log.barcodescanner"
}

run-sipdroid() {
  echo "=== START analyzing SipDroid"
  pushd $AB/SipDroid
  if  test ! -d sipdroid-3.1beta ; then
    unzip sipdroid-3.1beta.zip
  fi
  $CMD sipdroid-3.1beta android-8 SipDroid 2>&1 | tee log.sipdroid
  popd
  echo "=== END analyzing SipDroid; result saved to $AB/SipDroid/log.sipdroid"
}

run-xbmc() {
  echo "=== START analyzing XBMC"
  pushd $AB/XBMC
  if  test ! -d android-xbmcremote-0.8.8 ; then
    unzip XBMC-0.8.8.zip
  fi
  $CMD android-xbmcremote-0.8.8 android-10 XBMC 2>&1 | tee log.xbmc
  popd
  echo "=== END analyzing XBMC; result saved to $AB/XBMC/log.xbmc"
}

run-npr() {
  echo "=== START analyzing NPR"
  pushd $AB/NPR
  if  test ! -d npr-2.4 ; then
    unzip npr-2.4.zip
  fi
  $CMD npr-2.4/Npr android-8 NPR 2>&1 | tee log.npr
  popd
  echo "=== END analyzing NPR; result saved to $AB/NPR/log.npr"
}

run-mileage() {
  echo "=== START analyzing Mileage"
  pushd $AB/Mileage
  if  test ! -d android-mileage-3.1.1 ; then
    unzip android-mileage-3.1.1.zip
  fi
  $CMD android-mileage-3.1.1 android-10 Mileage 2>&1 | tee log.mileage
  popd
  echo "=== END analyzing Mileage; result saved to $AB/Mileage/log.mileage"
}

run-opensudoku() {
  echo "=== START analyzing OpenSudoku"
  pushd $AB/OpenSudoku
  if  test ! -d OpenSudoku-1558f8b ; then
    unzip OpenSudoku-1558f8b.zip
  fi
  $CMD OpenSudoku-1558f8b android-10 OpenSudoku 2>&1 | tee log.opensudoku
  popd
  echo "=== END analyzing OpenSudoku; result saved to $AB/OpenSudoku/log.opensudoku"
}

run-beem() {
  echo "=== START analyzing Beem"
  pushd $AB/Beem
  if  test ! -d Beem-0.1.8 ; then
    unzip Beem-0.1.8.zip
  fi
  $CMD Beem-0.1.8 android-16 Beem 2>&1 | tee log.beem
  popd
  echo "=== END analyzing Beem; result saved to $AB/Beem/log.beem"
}

run-supergenpass() {
  echo "=== START analyzing SuperGenPass"
  pushd $AB/SuperGenPass
  if  test ! -d SuperGenPass-2.2.2 ; then
    unzip SuperGenPass-2.2.2.zip
  fi
  $CMD SuperGenPass-2.2.2 android-16 SuperGenPass 2>&1 | tee log.supergenpass
  popd
  echo "=== END analyzing SuperGenPass; result saved to $AB/SuperGenPass/log.supergenpass"
}

# A simple usage printout
usage() {
  echo "Options: apv astrid connectbot fbreader keepassdroid k9 vlc vudroid tippytipper openmanager notepad mytracks barcodescanner sipdroid xbmc npr mileage opensudoku beem supergenpass"  echo "Example: $0 apv connectbot"
}

# "main"

# At least one program should be specified
if test $# -lt 1 ; then
  usage
  exit 1
fi

# For each specified program, run the analysis
for p in $*; do
  run-$p
done

