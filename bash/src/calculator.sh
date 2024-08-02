#!/bin/bash
calculate_factorial() {
if [ $1 -eq 1 ]; then
        echo 1
else
        local var=$(( $1 - 1 ))
        local res=$(calculate_factorial $var)
        echo $(( $res * $1 ))
fi
}

addition(){
        echo $1 + $2
}

read -p "Input calculation operand " operand

        case "$operand" in
                "+")
                        read -p "Input First Number: " first_number
                        read -p "Input Second Number: " second_number
                        echo "Result: $((first_number + second_number))"
                ;;

                "-")
                        read -p "Input First Number: " first_number
                        read -p "Input Second Number: " second_number
                        echo "Result: $((first_number - second_number))"
                ;;

                "*")
                        read -p "Input First Number: " first_number
                        read -p "Input Second Number: " second_number
                        echo "Result: $((first_number * second_number))"
                ;;

                "/")
                        read -p "Input First Number: " first_number
                        read -p "Input Second Number: " second_number
                        if [ $second_number -eq 0 ]; then
                                echo "You cannot enter 0 to denominator"
                                exit 0
                        fi
                        echo "Result: $((first_number / second_number))"

                ;;

                "%")
                        read -p "Input First Number: " first_number
                        read -p "Input Second Number: " second_number
                        echo "Result: $((first_number % second_number))"
                ;;

                "!")
                        read -p "Input the number that you want to calculate its factorial: " factorial_number
                        echo "Result: $(calculate_factorial $factorial_number)"
                ;;

                *)
                        echo "INVALID OPTION"
                ;;
        esac

d