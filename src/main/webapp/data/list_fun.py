county_file = './IL_county_list.txt'
with open(county_file,'r') as f:
  lines = f.readlines()
with open(county_file,'w') as f:
  for line in lines:
    f.write("\"")
    f.write(line[:-1])
    f.write("\",\n")