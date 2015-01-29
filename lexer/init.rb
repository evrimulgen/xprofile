#!/usr/bin/ruby
# get the results and put them in a hash

require 'nokogiri'

f = File.open('./lexer/scan_output.xml', 'r')
network_files = Nokogiri::XML(f)
f.close

f = File.open('./tmp/output/output-6432880145198347045.xml', 'r')
gui_files = Nokogiri::XML(f)
f.close

network = {} # map of file names to included packages

network_files.css('file').each do |f|
  k = f['path'].gsub('/', '.')[13..-6]
  v = []
  f.css('package').each do |p|
    v << p['name']
  end
  network[k] = v
end

# can obtain object id by hexadecimal conversion of id value on View element
# this reference will allow tokenization and can identify associated event handlers upon further analysis

gui_files.css('GUIHierarchy Activity').each do |activity|
  if network.has_key?(activity['name'])
    puts "\n-> Activity " + activity['name'].to_s
    puts "\n-> Package " + network[activity['name']].to_s
    puts "\n-> Views \n\n"
    activity.css('View').each do |v1|
      puts v1['type'] + ', ' + v1['idName']
      v1.css('View').each do |v2|
        puts "\t" + v2['type'] + ', ' + v2['idName']
        v2.css('View').each do |v3|
          puts "\t\t" + v3['type'] + ', ' + v3['idName']
          v3.css('View').each do |v4|
            puts "\t\t\t" + v4['type'] + ', ' + v4['idName']
            v4.css('EventAndHandler').each do |e|
              puts "\t\t\t\t" + e['event'] + " - " + e['handler']
            end
          end
        end
      end
    end
  end
end
