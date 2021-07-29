flag = "dctf{N0_way_y0u_gu3ss3d_that_w1thout_ch34t1ng}"

def solvable(rng):
    if min(rng.coefs) == 0: return False
    arr = [rng.next() for _ in range(20)]
    mat = [arr[i:i+10] for i in range(10)]
    for i in range(10):
        j = i
        while mat[i][i] == 0:
            j += 1
            if j >= 10: return False
            if mat[j][i] != 0: 
                mat[j], mat[i] = mat[i], mat[j]
                break

        q = pow(mat[i][i], -1, 5039)
        for j in range(i, 5): mat[i][j] = (mat[i][j] * q) % 5039
        for ix in range(i+1, 5):
            q = mat[ix][i]
            for jx in range(i, 5):
                mat[ix][jx] = (mat[ix][jx] - mat[i][jx] * q) % 5039
    return True