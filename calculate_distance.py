import csv
import math
import datetime


def distance(origin, destination):
    lat1, lon1 = origin
    lat2, lon2 = destination
    radius = 6371  # km

    dlat = math.radians(lat2-lat1)
    dlon = math.radians(lon2-lon1)
    a = math.sin(dlat/2) * math.sin(dlat/2) + math.cos(math.radians(lat1)) \
        * math.cos(math.radians(lat2)) * math.sin(dlon/2) * math.sin(dlon/2)
    c = 2 * math.atan2(math.sqrt(a), math.sqrt(1-a))
    d = radius * c

    return d


start = datetime.datetime(2020, 6, 10)
end = datetime.datetime(2020, 6, 17)

with open('offense_clean.csv', 'r') as offense:
    csvFile = csv.reader(offense)
    offense_map = {}

    next(csvFile)
    for lines in csvFile:
        time = lines[1]
        time_object = datetime.datetime.strptime(time, '%Y-%m-%d %H:%M:%S')
        if time_object >= start and time_object <= end:
            offenseId = lines[0]
            latitude, longtitude = float(lines[5]), float(lines[6])
            offense_map[offenseId] = (latitude, longtitude)

# offense_iterator = iter(offense_map.values())
# print(next(offense_iterator))
print(len(offense_map))

with open('listing_clean.csv', 'r') as listing:
    csvFile = csv.reader(listing)
    listing_map = {}

    next(csvFile)
    for lines in csvFile:
        listingId = lines[0]
        latitude, longtitude = float(lines[3]), float(lines[4])
        listing_map[listingId] = (latitude, longtitude)

# listing_iterator = iter(listing_map.values())
# print(next(listing_iterator))

within_distance_map = []
i = 0
for listing in listing_map:
    count = 0
    for offense in offense_map:
        if distance(listing_map[listing], offense_map[offense]) <= 0.5:
            i += 1
            count += 1
            within_distance_map.append((i, listing, offense))
    print('listing {} within distance {} total {}'.format(listing, offense,
          count))
print(i)

fields = ['RecordId', 'ListingId', 'OffenseId']
with open('withindistance.csv', 'w') as output:
    csvwriter = csv.writer(output)

    csvwriter.writerow(fields)
    for i in range(0, len(within_distance_map)):
        csvwriter.writerow(within_distance_map[i])
