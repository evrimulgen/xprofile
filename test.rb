#!/usr/bin/ruby
# parse every file in the source directory

require "./lexer/logger.rb"

file_in = ARGV[0] # get filename from command line arg

Logger = Logger.new('./src/source/com/shapes/shapes/About.java', ['android.net.uri'])
Logger.start # find files to parse
