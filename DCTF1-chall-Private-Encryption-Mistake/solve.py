from crypto.Util.number import isPrime

N_upper_bits = 0xba943b861cb40104742d131980ffca97a277976f94fb0a75632541f280d8bec944dc05d4de28305f62ce6201bf2481d0e542361bfd05bb988f3e2921dc9a14001185c8fc16b012e7a2174106da6f7d12e9ae16c196c2b08d97cb248ad17b1fac48591c4fdcd5b35547e097dc2406de04d2bf69074ca9cd62e09435c6c427cb8647ed365e09706280af7df95d390be10d452d6e5a2f62c6071b316082047b13ab42a5e4521f3ef549541af4ef96dfec37404524545b1fc5b4a54c9567bf9c6513cff7b3bb68cec7af6477e86fb735f525591e63e0058bb2ddb5adad35a1fff248adce85ca242fba03e2d05f29e68d0747f511ac83b4328058cfbc8720c04f0b1ec32deb638a708c792cc1e55b896f2cafc464c9eb

p_lower_bits = 0x644b7a5b0c84a8784fc8fd
q = 0xca6502f12507045331a4fa15f5b08b5e3d43e0b5fa881a45cebce8290212cee9cd68ede3e4e0ddc9ddc63fe9d90dd9d40cc91fc87fbdf66bb8040717f505c172003e6bb34b52b41e09cd1f1af7e79bec0187c0ae94c97b266846db50397bd184cf9cf3ced15865d7dbbf15552e5ee66875e839f4acaa437119ec54c6a10614e0fb85e1797d332516b794e9e85a044b1467098fda71e0693b3e4667c76e12d9b09bec651b8acbed10b90b7c0bd5706212bef2466dea2f006a40ad1a1a09ba6744bf7a379d333961aa1de5c91658ef343bdfa5501bca47fee74776540a6f125437fefed41d56447ecccc937835cfe44ba60566b65b42b05bc34a40d6f6899fa09d
dp = 0x805fbb4f153eb37e07c883429c8ed810540de8bc7e376c929bde8635860eacca3580f175570074446d93a0d25aece9bf03cd6d3466eaedefebacae4241e7f60065c57d6c61ee4ff44c6a3f6a7e02ef2147ff2cedb8556bc1b9dd65f50e5e3b4154b06ca54e5925ad412c0186207db3562a80096170a0563fabc56dabebdd5058639967ff4d7cad8bd1d5eb8355351565d48c99bab7812705e1f86f34448c77525eed78f281b5533c93e2d4f713fd28320d1530cf444f608bcb940dea4fd69eeece564e9d5f6a7bf9315ea9e0b9de91343f0958d64a8ad8ef17574c231f04ad4617b5a15461b87d548f5fc41e20f294c4a9105e0280149ac24a38fb08b1b9a4f9

dq_upper_bits = 0x7e57e62940d30b891f917c285f31f11e48226a514f6eccefcce9b2077f6adc4da9539a454c286f

e = 65537

for kp in range(3, e):
	p_mul = dp * e - 1
	if p_mul % kp == 0:
		p = (p_mul // kp) + 1
		if isPrime(p):
			print(f"Possible p: {p}")
			
N_upper_bits = 0xba943b861cb40104742d131980ffca97a277976f94fb0a75632541f280d8bec944dc05d4de28305f62ce6201bf2481d0e542361bfd05bb988f3e2921dc9a14001185c8fc16b012e7a2174106da6f7d12e9ae16c196c2b08d97cb248ad17b1fac48591c4fdcd5b35547e097dc2406de04d2bf69074ca9cd62e09435c6c427cb8647ed365e09706280af7df95d390be10d452d6e5a2f62c6071b316082047b13ab42a5e4521f3ef549541af4ef96dfec37404524545b1fc5b4a54c9567bf9c6513cff7b3bb68cec7af6477e86fb735f525591e63e0058bb2ddb5adad35a1fff248adce85ca242fba03e2d05f29e68d0747f511ac83b4328058cfbc8720c04f0b1ec32deb638a708c792cc1e55b896f2cafc464c9eb

p = 29791686445775073485378700470209688335718177706599767642432504083590603114910076374929535053482398336851605342797663683613325223634063629847424638028935759548191149276444459783992147191929135013836982345753448569845230229718165110551230345888691646705997950698867501969060964411380209431748935915067011799337626156789024820682099339362534433158645112827474678232631566274100969788543100022629142380969830130845896177796223051219120025584473048998525682854193337862016303825590875971178405543808856045166697591214691035232105537035578197042255791258084056674958796585835075868860711296172300516953713039694526325704957
q = 0xca6502f12507045331a4fa15f5b08b5e3d43e0b5fa881a45cebce8290212cee9cd68ede3e4e0ddc9ddc63fe9d90dd9d40cc91fc87fbdf66bb8040717f505c172003e6bb34b52b41e09cd1f1af7e79bec0187c0ae94c97b266846db50397bd184cf9cf3ced15865d7dbbf15552e5ee66875e839f4acaa437119ec54c6a10614e0fb85e1797d332516b794e9e85a044b1467098fda71e0693b3e4667c76e12d9b09bec651b8acbed10b90b7c0bd5706212bef2466dea2f006a40ad1a1a09ba6744bf7a379d333961aa1de5c91658ef343bdfa5501bca47fee74776540a6f125437fefed41d56447ecccc937835cfe44ba60566b65b42b05bc34a40d6f6899fa09d
N = p*q

assert hex(N).startswith(hex(N_upper_bits))

from Crypto.Util.number import isPrime
from Crypto.PublicKey import RSA

# Data recovered from the redacted PEM
N_upper_bits = 0xba943b861cb40104742d131980ffca97a277976f94fb0a75632541f280d8bec944dc05d4de28305f62ce6201bf2481d0e542361bfd05bb988f3e2921dc9a14001185c8fc16b012e7a2174106da6f7d12e9ae16c196c2b08d97cb248ad17b1fac48591c4fdcd5b35547e097dc2406de04d2bf69074ca9cd62e09435c6c427cb8647ed365e09706280af7df95d390be10d452d6e5a2f62c6071b316082047b13ab42a5e4521f3ef549541af4ef96dfec37404524545b1fc5b4a54c9567bf9c6513cff7b3bb68cec7af6477e86fb735f525591e63e0058bb2ddb5adad35a1fff248adce85ca242fba03e2d05f29e68d0747f511ac83b4328058cfbc8720c04f0b1ec32deb638a708c792cc1e55b896f2cafc464c9eb

p_lower_bits = 0x644b7a5b0c84a8784fc8fd
q = 0xca6502f12507045331a4fa15f5b08b5e3d43e0b5fa881a45cebce8290212cee9cd68ede3e4e0ddc9ddc63fe9d90dd9d40cc91fc87fbdf66bb8040717f505c172003e6bb34b52b41e09cd1f1af7e79bec0187c0ae94c97b266846db50397bd184cf9cf3ced15865d7dbbf15552e5ee66875e839f4acaa437119ec54c6a10614e0fb85e1797d332516b794e9e85a044b1467098fda71e0693b3e4667c76e12d9b09bec651b8acbed10b90b7c0bd5706212bef2466dea2f006a40ad1a1a09ba6744bf7a379d333961aa1de5c91658ef343bdfa5501bca47fee74776540a6f125437fefed41d56447ecccc937835cfe44ba60566b65b42b05bc34a40d6f6899fa09d
dp = 0x805fbb4f153eb37e07c883429c8ed810540de8bc7e376c929bde8635860eacca3580f175570074446d93a0d25aece9bf03cd6d3466eaedefebacae4241e7f60065c57d6c61ee4ff44c6a3f6a7e02ef2147ff2cedb8556bc1b9dd65f50e5e3b4154b06ca54e5925ad412c0186207db3562a80096170a0563fabc56dabebdd5058639967ff4d7cad8bd1d5eb8355351565d48c99bab7812705e1f86f34448c77525eed78f281b5533c93e2d4f713fd28320d1530cf444f608bcb940dea4fd69eeece564e9d5f6a7bf9315ea9e0b9de91343f0958d64a8ad8ef17574c231f04ad4617b5a15461b87d548f5fc41e20f294c4a9105e0280149ac24a38fb08b1b9a4f9

dq_upper_bits = 0x7e57e62940d30b891f917c285f31f11e48226a514f6eccefcce9b2077f6adc4da9539a454c286f

e = 65537

p = 29791686445775073485378700470209688335718177706599767642432504083590603114910076374929535053482398336851605342797663683613325223634063629847424638028935759548191149276444459783992147191929135013836982345753448569845230229718165110551230345888691646705997950698867501969060964411380209431748935915067011799337626156789024820682099339362534433158645112827474678232631566274100969788543100022629142380969830130845896177796223051219120025584473048998525682854193337862016303825590875971178405543808856045166697591214691035232105537035578197042255791258084056674958796585835075868860711296172300516953713039694526325704957
q = 0xca6502f12507045331a4fa15f5b08b5e3d43e0b5fa881a45cebce8290212cee9cd68ede3e4e0ddc9ddc63fe9d90dd9d40cc91fc87fbdf66bb8040717f505c172003e6bb34b52b41e09cd1f1af7e79bec0187c0ae94c97b266846db50397bd184cf9cf3ced15865d7dbbf15552e5ee66875e839f4acaa437119ec54c6a10614e0fb85e1797d332516b794e9e85a044b1467098fda71e0693b3e4667c76e12d9b09bec651b8acbed10b90b7c0bd5706212bef2466dea2f006a40ad1a1a09ba6744bf7a379d333961aa1de5c91658ef343bdfa5501bca47fee74776540a6f125437fefed41d56447ecccc937835cfe44ba60566b65b42b05bc34a40d6f6899fa09d
N = p*q

phi = (p-1)*(q-1)
d = pow(e,-1,phi)

# We have found the two prime factors of the modulus
assert isPrime(p) and isPrime(q) and p*q == N

# Our private exponent matches that from dp recovered
assert d % (p-1) == dp

# The top bits of the Modulus match those recovered
assert hex(N).startswith(hex(N_upper_bits))

# The prime p matches the low bits
assert hex(p).endswith(hex(p_lower_bits)[2:])

# The derived dq matches the recovered upper bits of dq
assert hex(d % (q-1)).startswith(hex(dq_upper_bits))

key = RSA.construct((N,e,d,p,q))
pem = key.export_key('PEM')
print(pem.decode())