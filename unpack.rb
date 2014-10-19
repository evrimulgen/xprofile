#!/usr/bin/ruby

require 'getoptlong'

parser = GetoptLong.new
parser.set_options(
	["-o", "--open",GetoptLong::REQUIRED_ARGUMENT], 
	["-l", "--lex",GetoptLong::OPTIONAL_ARGUMENT],
	["-a", "--apk",GetoptLong::REQUIRED_ARGUMENT]
)
begin
  opt, arg = parser.get_option
  case opt
    when "-o"
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
		Dir.glob "./src/source/*/*/*/*.java" do |f|
		  `ruby ./lexer/main.rb #{f}`
		end
	when "-l"
		puts "\n-> Performing lexical analysis ...\n\n"
		`rm -rf ./src/source/android/`
		`ruby ./lexer/main.rb ./src/source/*/*/*/*.java`
	when "-a"
		puts "\n-> Extracting dex from .apk file ..."
		`unzip -o #{arg} -d./tmp`		
		puts "-> Converting to .jar, 'dex2jar' ..."
		`./lib/dex2jar-0.0.9.15/d2j-dex2jar.sh ./tmp/classes.dex -o ./tmp/classes.jar 2>&1 | tee log`		
		puts "-> Extracting class files ..."
		`unzip -o ./tmp/classes.jar -d./src/classes`
		puts "-> Decompiling source files from .jar ...\n\n"
		`./lib/jad -o -r -sjava -d./src/source "./src/classes/**/*.class" 2>&1 | tee log`
	else
		puts "\nunpack.rb"
		puts "Usage:  ./unpack.rb [option(s)] [filename(s)]"
		puts "Options:\n\t-o\t - Open .apk file \t| --open [filepath]"
		puts "\t-l\t - Lexical analysis \t| --lex [filepath]"
		puts "\t-a\t - Decompile .apk \t| --apk [filepath]"
                puts "\nClean dirs with 'rake clean'\n\n"
  end
rescue => err
  puts err
end


