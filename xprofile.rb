#!/usr/bin/ruby

require 'getoptlong'

parser = GetoptLong.new
parser.set_options(
	["-p", "--profile",GetoptLong::REQUIRED_ARGUMENT],
	["-l", "--lexer",GetoptLong::OPTIONAL_ARGUMENT],
	["-s", "--src",GetoptLong::REQUIRED_ARGUMENT]
)
begin
	opt, arg = parser.get_option
	case opt
	when "-p"
		`mkdir ./tmp ./src`
		puts "\n-> Extracting dex from .apk file ..."
		`unzip -o #{arg} -d./tmp`
		puts "-> Converting to .jar, 'dex2jar' ..."
		`./lib/dex2jar-0.0.9.15/d2j-dex2jar.sh ./tmp/classes.dex -o ./tmp/classes.jar 2>&1 | tee log`
		puts "-> Extracting class files ..."
		`unzip -o ./tmp/classes.jar -d./src/classes`
		puts "-> Decompiling source files from .jar ..."
		`./lib/jad -o -r -sjava -d./src/source "./src/classes/**/*.class" 2>&1 | tee log`
		puts "-> Performing lexical analysis ...\n\n"
		`rm -rf ./src/source/android/`
		# *** check for subdirectories ***
		Dir.glob "./src/source/*/*/*/*.java" do |f|
			`ruby ./lexer/sweep.rb #{f}`
		end
	when "-l"
		puts "\n-> Performing lexical analysis ...\n\n"
		`rm -rf #{arg}/source/android/`
		Dir.glob "#{arg}/source/*/*/*/*.java" do |f|
			print `ruby ./lexer/sweep.rb #{f}`
		end
	when "-s"
		`mkdir ./tmp ./src`
		puts "\n-> Extracting dex from .apk file ..."
		`unzip -o #{arg} -d./tmp`
		puts "-> Converting to .jar, 'dex2jar' ..."
		`./lib/dex2jar-0.0.9.15/d2j-dex2jar.sh ./tmp/classes.dex -o ./tmp/classes.jar 2>&1 | tee log`
		puts "-> Extracting class files ..."
		`unzip -o ./tmp/classes.jar -d./src/classes`
		puts "-> Decompiling source files from .jar ...\n\n"
		`./lib/jad -o -r -sjava -d./src/source "./src/classes/**/*.class" 2>&1 | tee log`
	else
		puts "\nxprofile.rb"
		puts "Usage:  ./xprofile.rb [option(s)] [filename(s)]"
		puts "Options:\n\ \ -p\ \  - Profile Android .apk \ \ \ \ \ \ |\ \ --open [apk-filepath]"
		puts "\ \ -l\ \  - Use lexical analyzer \ \ \ \ \ \ |\ \ --lex [src-dir]"
		puts "\ \ -s\ \  - Decompile .apk to source \ \ |\ \ --apk [apk-filepath]"
		puts "\nClean project with 'rake clean'\n\n"
	end
rescue => err
	puts err
end