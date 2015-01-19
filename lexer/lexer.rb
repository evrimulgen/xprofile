#!/usr/bin/ruby
f = File.open('./lexer/scan_output.xml', 'r')
doc = Nokogiri::XML(f)
f.close
