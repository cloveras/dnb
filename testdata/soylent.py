#!/usr/local/bin/python3
""" Quick hack to generate fake people and some data.

TODO:
- Valid Norwegian SSNs
- Bank accounts: One or more per person
- Debit cards: One or more per person
- Credit cards: Zero or more per person
- Transactions (this is the interesting part):
    - Salary
    - Mortgage (for some)
    - Insurance (for some)
    - Utilities (electricity, public fees, etc)
    - Car related expenses (for some)
    - Food, transport, other everyday transactions
    - Various other purchases
    - Ideally: In segments/groups, with patterns, etc (endless possibilities to make it "realistic")

License:

This program is free software: you can redistribute it and/or modify it under
the terms of the GNU General Public License as published by the Free Software
Foundation, either version 3 of the License, or (at your option) any later
version.

This program is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
You should have received a copy of the GNU General Public License along with
this program. If not, see <http://www.gnu.org/licenses/>.
"""

__author__ = "Christian Løverås"
__contact__ = "cl@dnb.no"
__copyright__ = "Copyright 2018, DNB Open Banking"
__license__ = "GPLv3"
__status__ = "Hack"
__version__ = "0.0.1"

import requests
import json
import random
import datetime
from random import randrange
from random import choice
from faker import Faker

def get_random_date():
    max_age = 100
    this_year = datetime.datetime.now().year
    random_year = randrange(this_year - max_age, this_year)
    try:
        return datetime.datetime.strptime('{} {}'.format(random.randint(1, 366), random_year), '%j %Y')
    # Leap year? Try again.
    except ValueError:
        get_random_date(random_year)

def create_people (number_of_people):
    '''
    :param number_of_people: How many people should be created?
    :return:
    '''

    fake = Faker('no_NO')

    # Person data for reuse in loop below
    person = ''
    day = ''
    month = ''
    year = ''
    date_of_birth = ''
    first_name = ''
    last_name = ''
    gender = ''
    street = ''
    postal_code = ''
    city = ''
    phone = ''
    email = ''
    customer_data = ''

    # Files to create, or append to
    customerFileTxt = open('customers-generated.txt', 'a')
    customerFileJson = open('customers-generated.json', 'a')

    # People of the world!
    for i in range(number_of_people):

        # Get random birth date as timestamp
        random_date = get_random_date()
        year, month, day = [random_date.year, random_date.month, random_date.day]
        date_of_birth = str(year) + '-' + str(month).zfill(2) + '-' + str(day).zfill(2) + 'T00:00:00.000Z'

        # SSN: Faking it until Faker supports Norwegian SSNs: https://github.com/joke2k/faker/issues/714
        pnr = str(randrange(10000,99999)).zfill(6)
        ssn = str(day).zfill(2) + str(month).zfill(2) + str(year)[-2] + pnr

        # Name and gender
        if random.choice((True,False)):
            gender = 'Female'
            first_name = fake.first_name_female()
        else:
            gender = 'Male'
            first_name = fake.first_name_male()
        last_name = fake.last_name()

        # Contact information
        street = fake.street_name() + ' ' + fake.building_number()
        postal_code = fake.postcode()
        city = fake.city()
        phone = fake.phone_number()
        email = fake.safe_email()
        id_type = random.choice(('passport', 'driverslicense', 'nationalidcard'))

        # JSON
        person = {
            'personalast_nameumber': ssn,
            'firstName': first_name,
            'lastName': last_name,
            'dateOfBirth': date_of_birth,
            'gender': gender,
            'nationality': 'Norwegian',
            'address': {
                'street': street,
                'postalCode': postal_code,
                'city': city,
                'country': 'NO'
            },
            'phoneNumber': phone,
            'email': email,
            'idType': id_type
        }
        data = json.dumps(person)

        # Write to files in JSON and text format
        customerFileJson.write(data + '\n')
        customerFileTxt.write('%r, %r, %r, %r, %r, %r, %r, %r, %r, %r\n' %
                              (ssn, first_name, last_name, gender, street, postal_code, city, phone, email, id_type))

        # Vewry verbose
        print('%r, %r, %r, %r, %r, %r, %r, %r, %r, %r' %
              (ssn, first_name, last_name, gender, street, postal_code, city, phone, email, id_type))

# Business time
create_people(10)
