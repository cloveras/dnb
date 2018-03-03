import requests
import json
import random
from random import randrange
from random import choice
from faker import Faker

fake = Faker('no_NO')
person = ''
numberOfPeople = 100

customerFileTxt = open('customers-generated.txt', 'a')
customerFileJson = open('customers-generated.json', 'a')

url = 'https://dnbapistore.com/hackathon/customers/3.0/customer'
apiKey = 'KEY_GOES_HERE'
head = {'Accept': 'application/json', 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + appKey}

for i in range(numberOfPeople):

    # Birth date, etc
    day = randrange(1,25)
    month = randrange(1,12)
    year = randrange(1900,2018)
    dateOfBirth = str(year) + '-' + str(month).zfill(2) + '-' + str(day).zfill(2) + 'T00:00:00.000Z'
    pnr = str(randrange(10000,99999)).zfill(6) # Faking it. https://no.wikipedia.org/wiki/F%C3%B8dselsnummer
    ssn = str(day).zfill(2) + str(month).zfill(2) + str(year)[-2] + pnr

    # Name
    if random.choice((True,False)):
        gender = 'Female'
        fn = fake.first_name_female()
    else:
        gender = 'Male'
        fn = fake.first_name_male()
    ln = fake.last_name()

    print(ssn + ' ' + fn + ' ' + ln)

    person = {
        'personalNumber': ssn,
        'firstName': fn,
        'lastName': ln,
        'dateOfBirth': dateOfBirth,
        'gender': gender,
        'nationality': 'Norwegian',
        'address': {
            'street': fake.street_name() + ' ' + fake.building_number(),
            'postalCode': fake.postcode(),
            'city': fake.city(),
            'country': 'NO'
        },
        'phoneNumber': fake.phone_number(),
        'email': fake.safe_email(),
        'idNumber': str(randrange(0,999999)).zfill(6),
        'idType': 'passport'
    }
    data = json.dumps(person)

    try:
        requests.post(url, data=data, headers=head)
    except requests.exceptions.RequestException as e:
        print(e)
        sys.exit(1)

    customerFileJson.write(data + '\n')
    customerFileTxt.write(ssn + ' ' + fn + ' ' + ln + '\n')
