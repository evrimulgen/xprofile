# parser.rb

dir = File.dirname(__FILE__)
require "#{dir}/scanner.rb"

class Parser
  @next = {}   
  @package_list = []

  def initialize(filename)  	
    @scanner = Scanner.new(filename)
    @package_list = Regexp.union([
      /android.net.Uri/,
      /java.net.*/,
      /android.net.*/,
      /java.nio.*/,
      /org.apache.http.*/
    ])
  end

  def next!
    @next = @scanner.get_next_token 
    abort if @next['lexeme'] == "EOF"
    # puts @next
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
        puts "Found #{@next['lexeme']} package."	
      end
    end
  end  
end