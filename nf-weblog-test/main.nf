process HELLO {
    output:
    stdout

    script:
    "echo hello"
}

workflow {
    HELLO()
}
