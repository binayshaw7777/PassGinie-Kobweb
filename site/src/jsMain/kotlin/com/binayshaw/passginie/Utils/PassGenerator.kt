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
    val symbols = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?"
    var allCharacters: String = buildString {
        if (shouldIncludeUppercase) append(uppercaseLetters)
        if (shouldIncludeLowercase) append(lowercaseLetters)
        if (shouldIncludeNumbers) append(numbers)
        if (shouldIncludeSymbols) append(symbols)
    }

    if (allCharacters.isEmpty()) {
        allCharacters += lowercaseLetters
    }

    val password = buildString {
        repeat(passwordLength) {
            val randomIndex = (0..allCharacters.length - 1).random()
            append(allCharacters[randomIndex])
        }
    }

    return password
}