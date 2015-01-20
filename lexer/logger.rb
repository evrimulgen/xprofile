#!/usr/bin/ruby
# logger.rb
# parses a package for methods that could be used for network communication

dir = File.dirname(__FILE__)
require "#{dir}/scanner.rb"

class Logger

  @packages = []

  def initialize(filename, packages)
    @scanner = Scanner.new(filename)
    @packages = packages
    @class_list = {
      /java.net.*/ => Regexp.union([
        /uri/,
        /http/,
        /urlconnection/,
        /httpurlconnection/,
        /socket/,
        /datagrampacket/,
        /datagramsocket/,
        /multicastsocket/
      ]),
      /android.net.*/ => Regexp.union([
        /uri/,
        /http/,
        /urlconnection/,
        /httpurlconnection/,
        /socket/,
        /datagrampacket/,
        /datagramsocket/,
        /multicastsocket/
        ]),
      /java.nio.*/ => Regexp.union([
        /buffer/,
        /channel/,
        /selector/
      ]),
      /org.apache.http/ => Regexp.union([
        /httpclientconnection/,
        /httpconnection/,
        /httpentity/,
        /httpinetconnection/,
        /httprequest/,
        /httpserverconnection/
      ])
    }
  end

  def next!
    @next = @scanner.get_next_token
    @next = nil if @next['lexeme'] == "EOF"
  end

  def check(token_class, lexeme)
    return (token_class == @next['class'] && lexeme == @next['lexeme']) unless @next.nil?
  end

  def check_for_classes
    if @packages.size > 1
      if @next.nil? == false && @next['class'] == "identifier"
        @packages.each do |p|
          return true if @next['lexeme'].match(@class_list[p])
        end
      end
    else
      p = @packages.first
      key = ''
      @class_list.keys.each { |k| key = k if p.match(k) }
      return (@next['class'] == "identifier" && @next['lexeme'].match(@class_list[key])) unless @next.nil?
    end
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
      if check_for_classes
        puts @next
        puts lookahead(2).inspect
      end
    end
  end

  def lookahead(k)
    result = []
    for i in 1..k
      next!
      result.push @next
    end
    return result
  end

end
