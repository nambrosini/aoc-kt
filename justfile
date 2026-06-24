export JAVA_HOME := `(/usr/libexec/java_home -v 21)`

run year day:
    ./gradlew -q run -PmainClass="aoc{{year}}.Day$(printf '%02d' {{day}})Kt"

scaffold year day:
    #!/usr/bin/env bash
    set -e
    padded=$(printf '%02d' {{day}})
    mkdir -p src/main/kotlin/aoc{{year}}
    mkdir -p src/test/kotlin/aoc{{year}}
    sed -e 's/__YEAR__/{{year}}/g' -e "s/__DAY__/$padded/g" -e 's/__DAY_UNPADDED__/{{day}}/g' \
        templates/Day.kt > "src/main/kotlin/aoc{{year}}/Day${padded}.kt"
    sed -e 's/__YEAR__/{{year}}/g' -e "s/__DAY__/$padded/g" -e 's/__DAY_UNPADDED__/{{day}}/g' \
        templates/DayTest.kt > "src/test/kotlin/aoc{{year}}/Day${padded}Test.kt"
    echo "Scaffolded aoc{{year}}/Day${padded}"

download year day:
    #!/usr/bin/env bash
    set -e
    mkdir -p data/input/{{year}}
    curl -s -o "data/input/{{year}}/{{day}}.txt" \
        -H "Cookie: session=$(cat ~/.adventofcode.session)" \
        "https://adventofcode.com/{{year}}/day/{{day}}/input"
    echo "Downloaded input for {{year}} day {{day}}"

puzzle year day:
    #!/usr/bin/env bash
    set -e
    mkdir -p data/puzzles/{{year}}
    curl -s \
        -H "Cookie: session=$(cat ~/.adventofcode.session)" \
        "https://adventofcode.com/{{year}}/day/{{day}}" \
    | pandoc -f html -t markdown -o "data/puzzles/{{year}}/{{day}}.md"
    echo "Downloaded puzzle for {{year}} day {{day}}"

new year day:
    just scaffold {{year}} {{day}}
    just download {{year}} {{day}}
    just puzzle {{year}} {{day}}

lint:
    ./gradlew ktlintCheck

format:
    ./gradlew ktlintFormat
