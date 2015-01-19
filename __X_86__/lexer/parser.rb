# parser.rb
# Abstract:  LL(1) Recursive Descent parser
#   Uses scanner to build abstract syntax tree
#   Grammar requirements for each method are stated in BNF notaion above

dir = File.dirname(__FILE__)
require "#{dir}/scanner.rb"

class Parser
  @next = {}   

  def initialize(filename)  	
    @scanner = Scanner.new(filename)    
  end

  def next!
    @next = @scanner.get_next_token 
	abort if @next['lexeme'] == "EOF"
    puts @next
  end

  def check(token_class, lexeme)
    return (token_class == @next['class'] && lexeme == @next['lexeme']) unless @next.nil?
  end

  def error(str)
    puts "=> Parse error [line #{@scanner.line}, col #{@scanner.col}]:  \"invalid #{str}\" for token '#{@next['lexeme']}'."
  end

  def start
    return package 
  end
  
  def package
    next! until check("identifier", "android.net.Uri")	
	if check("identifier", "android.net.Uri")	  
	  abort "Found android.net.* package."	
	else
	  abort "Package not used in this class."
	end
  end  
end