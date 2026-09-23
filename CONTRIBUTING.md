# nf-weblog

Contributions are welcome. Fork [this repository](https://github.com/nextflow-io/nf-weblog) and open a pull request to propose changes. Consider submitting an [issue](https://github.com/nextflow-io/nf-weblog/issues/new) to discuss any proposed changes with the maintainers before submitting a pull request.

## Development

Build and install the plugin to your local Nextflow installation:

```bash
make install
```

Start the test endpoint, which prints every POST request it receives:

```bash
./echo.py
```

In another terminal, run the test pipeline:

```bash
nextflow run nf-weblog-test -plugins nf-weblog@<version>
```

## Publishing

Follow these steps to package, upload, and publish the plugin:

1. Update the [version file](./VERSION).

2. Run `make release` to build and publish the plugin.

3. Make a [GitHub release](https://github.com/nextflow-io/nf-weblog/releases).
