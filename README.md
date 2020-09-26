# StepStone API Task

Application building by JHipster, uses JDK 11.

## Development

To start your application in the dev profile, run:

```
./mvnw
```

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

## Building for production

### Packaging as jar

To build the final jar and optimize the Decerto application for production, run:

```

./mvnw -Pprod clean verify


```

To ensure everything worked, run:

```

java -jar target/*.jar


```

## How to use app

To test application, use postman: [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

Next,
Send POST( http://localhost:8080/api/weather/) request with next body:

```
[
{
    "providerType": "WEATHER_BIT",
    "citiesList": ["Gdansk", "Warsaw"]
},
{
    "providerType": "ACCU_WEATHER",
    "citiesList": ["Gdansk", "Warsaw"]

},
{
    "providerType": "OPEN_WEATHER",
    "citiesList": ["Gdansk", "Warsaw"]

}
]
```

```
In this context we have next types:
- OPEN_WEATHER - Open Weather
- ACCU_WEATHER - Accu Weather
- WEATHER_BIT - Weather Bit
- ALL - if you want to see result from all providers
```

Then, we got response :

```
[
    {
        "providerName": "bitWeather",
        "providerResult": {
            "Warsaw": "21.7",
            "Gdansk": "18.1"
        }
    },
    {
        "providerName": "accuWeather",
        "providerResult": {
            "Warsaw": "71F",
            "Gdansk": "71F"
        }
    },
    {
        "providerName": "openWeather",
        "providerResult": {
            "Warsaw": "294.19",
            "Gdansk": "294.04"
        }
    }
]
```

NOTE:
Please, if you have any problems with existing Api keys (every weather source generate API_KEY)
Change api keys in `src/main/resources/config/application.yml`

```
stepstone:
  accu-weather: KEY;URL
  weather-bit: KEY;URL
  open-weather: KEY;URL
```
