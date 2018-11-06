// var secureRandom = require('secure-random');

function randrange(min, max) {
    return Math.floor(Math.random()*(max-min)) + min;
}

function intFromBytes(bytes) {
    var val = new Uint32Array(1);
    for(var i = 0; i < bytes.length; i++) {
        val[0] += bytes[i];
        if(i < bytes.length-1) {
            val[0] = val[0] << 8;
        }
    }
    return val[0];
}

function is_prime(number) {
    /*
     This function uses Rabin-Miller primality test
      */

    // Number of tests -> prob(the number is composite) < 2^(-2*num_tests)
    var num_tests = 64;

    // Suppose number = 2^t*s + 1

    // Calculate t and s
    var s = number - 1;
    var t = 0;
    while(s%2 == 0) {
        s = s/2;
        t += 1;
    }

    for(var i = 0; i < num_tests; i++) {
        // Choose number a randomly
        var a = randrange(1, number);

        // Check if number meets the properties
            // Check first property
        if(mod_exp(a, s, number) == 1)
            continue;

            // If it doesn't meet the first property, then check the second property
        var found = false;
        for(var j = 0; j < t; j++) {
            if(mod_exp(a, s*exp(2, j), number) == number-1) {
                found = true;
                break;
            }
        }

        // Return false if none passed
        if(!found)
            return false;
    }
    return true;
}

function find_prime_near(x) {
    /*
     Find a prime number near x
     */

    while(true) {
        if(is_prime(x))
            return x;
        x++;
    }
}

function generate_prime(numBits) {
    /*
     Generate a prime number with numBits bits
     */

    // Generate a random number with numBits bits
    var rand_num = intFromBytes(secureRandom.randomArray(numBits/8));
    return find_prime_near(rand_num);
}

function is_primitive_root(number, prime) {
    var set = new Set();
    for(var i = 1; i < prime; i++) {
        var temp = mod_exp(number, i, prime);
        if(set.has(temp))
            return false;
        set.add(temp);
    }
    return true;
}

function first_primitive_root(prime) {
    for(var i = 2; i < prime; i++) {
        if(is_primitive_root(i, prime))
            return i;
    }
}

function primitive_roots(prime) {
    var roots = [];
    for(var i = 2; i < prime; i++) {
        if(is_primitive_root(i, prime))
            roots.push(i);
    }
    return roots;
}

function square(x) {
    return x*x;
}

function exp(base, exponent) {
    if(exponent == 0) return 1;
    if(exponent % 2 == 0)
        return square(exp(base, exponent/2));
    else
        return base*exp(base, exponent-1);
}

function mod_exp(base, exponent, prime) {
    if(exponent == 0) return 1;
    if(exponent % 2 == 0)
        return square(mod_exp(base, exponent/2, prime)) % prime;
    else
        return base*mod_exp(base, exponent-1, prime) % prime;
}

function generate_private_number(numBits) {

    return intFromBytes(secureRandom.randomArray(numBits));
}

// var p = generate_prime(32);
// console.log(p);
// var x = generate_private_number(32);
// console.log(x);
// var g = first_primitive_root(p);
// console.log(g);
// var y = mod_exp(g, x, p);
// console.log(y);