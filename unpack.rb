!#/usr/bin/ruby

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
		`unzip #{arg} -d./tmp`		
		puts "-> Converting to .jar, 'dex2jar' ...\n\n"
		`./lib/dex2jar-0.0.9.15/d2j-dex2jar.sh ./tmp/classes.dex -o ./tmp/classes.jar`		
		puts "\n-> Extracting class files ..."
		`unzip ./tmp/classes.jar -d./src/classes`
		puts "\n-> Decompiling source files from .jar ...\n\n"
		`./lib/jad -o -r -sjava -d./src/source "./src/classes/**/*.class"`
		puts "\n-> Performing lexical analysis ... \n\n"
		`rm -rf ./src/source/android/`
		`ruby ./lexer/main.rb ./src/source/*/*/*/*.java`
	when "-l"
		puts "\n-> Performing lexical analysis ... \n\n"
		`rm -rf ./src/source/android/`
		`ruby ./lexer/main.rb ./src/source/*/*/*/*.java`
	when "-a"
		puts "\n-> Extracting dex from .apk file ..."
		`unzip #{arg} -d./tmp`		
		puts "-> Converting to .jar, 'dex2jar' ...\n\n"
		`./lib/dex2jar-0.0.9.15/d2j-dex2jar.sh ./tmp/classes.dex -o ./tmp/classes.jar`		
		puts "\n-> Extracting class files ..."
		`unzip ./tmp/classes.jar -d./src/classes`
		puts "\n-> Decompiling source files from .jar ...\n\n"
		`./lib/jad -o -r -sjava -d./src/source "./src/classes/**/*.class"`
	else
		puts "\nunpack.rb"
		puts "Usage:  ruby unpack.rb [option(s)] [filename(s)]"
		puts "Options:\n\t-o\t - Open .apk file \t| --open [filepath]"
		puts "\t-l\t - Lexical analysis \t| --lex [filepath]"
		puts "\t-a\t - Decompile .apk \t| --apk [filepath]"
  end
rescue => err
  puts err
end


