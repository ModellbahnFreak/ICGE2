SEPERATOR_P="package de.unistuttgart.informatik.fius.icge"
SEPERATOR_M="module de.unistuttgart.informatik.fius.icge"
SEPERATOR_OM="open module de.unistuttgart.informatik.fius.icge"

dir="$(dirname "$(realpath "$0")")"

function fail {
  echo $1
  exit $2
}

replace=$(cat "$dir/correctHeader.txt")

searchP="*$SEPERATOR_P"
replaceP=$(printf "$replace\n$SEPERATOR_P")

searchM="*$SEPERATOR_M"
replaceM=$(printf "$replace\n$SEPERATOR_M")

searchOM="*$SEPERATOR_OM"
replaceOM=$(printf "$replace\n$SEPERATOR_OM")

buffer=$(cat "$1")
bufLen=${#buffer}

bufferP="${buffer##$searchP}"
bufLenP=${#bufferP}

bufferM="${buffer##$searchM}"
bufLenM=${#bufferM}

bufferOM="${buffer##$searchOM}"
bufLenOM=${#bufferOM}


result=""

if [ $bufLenP -lt $bufLen ] ;then
  result="$replaceP$bufferP"
elif [ $bufLenOM -lt $bufLen ] ;then
  result="$replaceOM$bufferOM" 
elif [ $bufLenM -lt $bufLen ] ;then
  result="$replaceM$bufferM"
else
  fail "Could not find sperator in $1" 1
fi

echo "$result" > "$1"
