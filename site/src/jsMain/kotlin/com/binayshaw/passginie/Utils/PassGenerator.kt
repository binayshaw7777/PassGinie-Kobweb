import java.security.SecureRandom

fun passGenerator(
    passwordLength: Int,
    shouldIncludeUppercase: Boolean,
    shouldIncludeLowercase: Boolean,
    shouldIncludeNumbers: Boolean,
    shouldIncludeSymbols: Boolean,
): String {
    val uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val lowercaseLetters = "abcdefghijklmnopqrstuvwxyz"
    val numbers = "0123456789"
    val symbols = "!@#$%^&*()_+-=[]{}|;':\"<>,.?/~"

    val paramsAreFalse = shouldIncludeLowercase.not() && shouldIncludeUppercase.not() && shouldIncludeNumbers.not()

    val allowedChars = mutableListOf<Char>()
    if (shouldIncludeUppercase) allowedChars.addAll(uppercaseLetters.toList())
    if (shouldIncludeLowercase || paramsAreFalse) allowedChars.addAll(lowercaseLetters.toList())
    if (shouldIncludeNumbers) allowedChars.addAll(numbers.toList())
    if (shouldIncludeSymbols) allowedChars.addAll(symbols.toList())

    val random = SecureRandom()
    val password = StringBuilder(passwordLength)
    repeat(passwordLength) {
        password.append(allowedChars[random.nextInt(allowedChars.size)])
    }
    return password.toString()
}