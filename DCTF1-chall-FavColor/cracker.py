import math

targets = [94, 124, 237, 26, 94, 141, 87, 202]

crackedStr = ""
crackedNum = ""

for target in targets:
    for k in range(1000):
        # 13x + y = 256k + target
        # Iscemo celostevilske resitve za x ki so ASCII znaki
        # Pomagamo si lahko z y
        x = (256*k + target + 0) / 13.0
        x_fl = int(math.floor(x))
        # Ali je x_fl dopustna ASCII vrednost?
        if ((x_fl > 64 and x_fl < 91) or (x_fl > 96 and x_fl < 123)):
            # Ali je x_fl enak x in je x potemtakem celo stevilo?
            if x_fl == x:
                crackedStr += str(chr(x))
                crackedNum += str('0')
                break
            # Ali si lahko sicer malo pomagamo z y?
            else:
                y = 256*k + target - 13*x_fl
                if y < 10:
                    crackedStr += str(chr(x_fl))
                    crackedNum += str(y)
                    break
print(crackedStr)
print(crackedNum)