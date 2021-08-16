import csv

with open('location_clean.csv', 'r') as location:
    csvFile = csv.reader(location)
    location = []

    for lines in csvFile:
        pair = []
        pair.append(lines[0])
        pair.append(lines[1])
        location.append(pair)
print(len(location))

with open('listings.csv', 'r') as file:
    csvFile = csv.reader(file)
    i = 0

    next(csvFile)
    for lines in csvFile:
        i += 1
        line = []
        line.append(lines[2])
        line.append(lines[3])
        if line not in location:
            location.append(line)
print(len(location))
print(location[0])


with open('location_whole.csv', 'w') as output:
    csvwriter = csv.writer(output)

    for i in range(len(location)):
        csvwriter.writerow(location[i])
