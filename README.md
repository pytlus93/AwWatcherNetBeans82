<img width="100" height="100" src="/assets/images/logo.png" alt="ActivityWatchLogo" />

# AwWatcherNetBeans82

This extension allows [ActivityWatch](https://activitywatch.net), the free and open-source time tracker, to keep track of the projects and programming languages you use in NetBeans IDE.

## Features

Sends following data to ActivityWatch:
- current project name
- current file name
- programming language (by current file extension)

## Requirements

This extension requires ActivityWatch to be running on your machine.

## Installation

1. Download the `.nbm` plugin from [GitHub releases](https://github.com/pytlus93/AwWatcherNetBeans82/releases/latest).

2. Inside your IDE, select `Tools` -> `Plugins` -> `Downloaded` -> `Add Plugins...`

3. Select the downloaded nbm file.

4. Check `AwWatcherNetBeans` and click the `Install` button.
   
5. Follow the wizard instructions to complete the installation.

6. Use your IDE like you normally do and your time will be tracked for you automatically.

7. Visit [http://localhost:5600](http://localhost:5600) to see your logged time.

## Extension Settings

![Configuration Dialog](/assets/images/ConfigDialog.png)

- Frequency
  - Allows to setup frequency of sending [hearbeats](https://docs.activitywatch.net/en/latest/buckets-and-events.html#heartbeats) in range <1;60>\
Default: 5s

- Pulse time
  - Allows to setup Pulse Time (size of timewindow between events that will be merged in one event) in range <1;120>\
Default: 20s

- Debug mode
  - Allows to use Activity Watch test mode, it means use testing server url `localhost:5666` instead of `localhost:5600`\
Default: False

- Custom File Types
  - Allows to define custom defined languages or reassing file extension to another language.\
Enter file extension and corresponding language in format `<ext>=<lang>` one per line.
  - Examples:
    - IFS Development
      ```
      entity=IFS Marble Entity
      enumeration=IFS Marble  Entity
      utility=IFS Marble Entity
      storage=IFS Marble Entity
      views=IFS Marble Entity
      serverpackage=IFS Marble Middle-Tier
      report=IFS Report
      rdf=IFS Report
      cdb=IFS PL/SQL
      ins=IFS PL/SQL
      cre=IFS PL/SQL
      ```

## Development

### API Client

Client is generated from [Swagger definition](http://localhost:5600/api/swagger.json) via SwaggerHub ([My project for ActivityWatch REST API v1.0](https://app.swaggerhub.com/apis/InfFilipV/ActivityWatch/1.0)).


## Special Thanks

- [netbeans-wakatime](https://github.com/wakatime/netbeans-wakatime)
- [aw-watcher-vscode](https://github.com/ActivityWatch/aw-watcher-vscode/)

## Release Notes

### 1.1.0

Updated to Java 14
It also added support to NetBeans 12

### 1.0.0

First version