#!/usr/bin/ruby

dir = File.dirname(__FILE__)
require "#{dir}/parser.rb"

file_in = ARGV[0] # get filename from command line arg
parser = Parser.new(file_in)
parser.start()
