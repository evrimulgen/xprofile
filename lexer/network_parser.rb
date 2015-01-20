#!/usr/bin/ruby
# network_parser.rb
# scan file for network packages

dir = File.dirname(__FILE__)
require "#{dir}/scanner.rb"

class NetworkParser
  @next = {}
  @package_list = []

  def initialize(filename, f)
    @scanner = Scanner.new(filename)
    @package_list = Regexp.union([
      /android.net.Uri/,
      /java.net.*/,
      /android.net.*/,
      /java.nio.*/,
      /org.apache.http.*/
    ])
    @file = f
  end

  def next!
    @next = @scanner.get_next_token
    @next = nil if @next['lexeme'] == "EOF"
  end

  def check(token_class, lexeme)
    return (token_class == @next['class'] && lexeme == @next['lexeme']) unless @next.nil?
  end

  def check_for_packages
    return (@next['class'] == "identifier" && @next['lexeme'].match(@package_list)) unless @next.nil?
  end

  def error(str)
    puts "=> Parse error [line #{@scanner.line}, col #{@scanner.col}]:  \"invalid #{str}\" for token '#{@next['lexeme']}'."
  end

  def start
    next!
    package
  end

  def package
    until @next.nil?
      next!
      if check_for_packages
        @file.puts("\t<package name=\"#{@next['lexeme']}\"/>")
      end
    end
  end
end
