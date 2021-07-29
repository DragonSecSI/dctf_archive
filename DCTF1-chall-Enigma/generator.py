from random import SystemRandom
from enigma.machine import EnigmaMachine

random = SystemRandom()

FLAG = "DCTFTURINGWOULDBEPROUD"

output = open("cipher.txt", "w")

for i in range(10000):
    ROTORS = [random.choice(("I","II","III","IV","V","VI","VII","VIII")) for _ in range(3)] #pick 3
    REFLECTOR = random.choice(("B", "C", "B-Thin", "C-Thin"))  #pick 1
    RING_SETTINGS = [random.randrange(26) for _ in range(3)]   #pick 3 from 26

    machine = EnigmaMachine.from_key_sheet(
           rotors=ROTORS,
           reflector=REFLECTOR,
           ring_settings=RING_SETTINGS,
           plugboard_settings="")
    output.write(machine.process_text(FLAG) + "\n")