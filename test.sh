kotlin_version=$1
kotlin_repo_url=$2

printf "The Argument kotlin_version is %s\n" "$kotlin_version"
printf "The Argument kotlin_repo_url is %s\n" "$kotlin_repo_url"

printf "Check browser-example\n"
cd browser-example
./gradlew assemble -Pkotlin_version=$kotlin_version -Pkotlin_repo_url=$kotlin_repo_url

if [ $? -eq 0 ]
then
  echo "Successfully"
else
  exit $?
fi

cd ..

printf "Check compose-example\n"
cd compose-example
./gradlew wasmJsBrowserDistribution -Pkotlin_version=$kotlin_version -Pkotlin_repo_url=$kotlin_repo_url

if [ $? -eq 0 ]
then
  echo "Successfully"
else
  exit $?
fi

cd ..

printf "Check nodejs-example\n"
cd nodejs-example
./gradlew build -Pkotlin_version=$kotlin_version -Pkotlin_repo_url=$kotlin_repo_url
./gradlew wasmJsNodeDevelopmentRun -Pkotlin_version=$kotlin_version -Pkotlin_repo_url=$kotlin_repo_url
./gradlew wasmJsNodeProductionRun -Pkotlin_version=$kotlin_version -Pkotlin_repo_url=$kotlin_repo_url

if [ $? -eq 0 ]
then
  echo "Successfully"
else
  exit $?
fi

cd ..

printf "Check wasi-example\n"
cd wasi-example
./gradlew build -Pkotlin_version=$kotlin_version -Pkotlin_repo_url=$kotlin_repo_url
./gradlew wasmWasiNodeDevelopmentRun -Pkotlin_version=$kotlin_version -Pkotlin_repo_url=$kotlin_repo_url
./gradlew wasmWasiNodeProductionRun -Pkotlin_version=$kotlin_version -Pkotlin_repo_url=$kotlin_repo_url
./gradlew wasmWasiDenoDevelopmentRun -Pkotlin_version=$kotlin_version -Pkotlin_repo_url=$kotlin_repo_url
./gradlew wasmWasiDenoProductionRun -Pkotlin_version=$kotlin_version -Pkotlin_repo_url=$kotlin_repo_url

if [ $? -eq 0 ]
then
  echo "Successfully"
else
  exit $?
fi

cd ..
