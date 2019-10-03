#!/bin/bash

make test > test_result.txt 2> /dev/null

diff test_result.txt tests/expected.txt

if [ "$?" == 0 ]; then
  echo -e "\e[32mTESTS PASSED\e[0m"
else
  echo -e "\e[31mTESTS FAILED\e[0m"
fi

rm test_result.txt
