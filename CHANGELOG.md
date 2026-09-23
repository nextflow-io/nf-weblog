# Changelog

All notable changes to the nf-weblog plugin will be documented here.

See [Keep a Changelog](http://keepachangelog.com/) for recommendations on how to structure this file.

## [1.2.0] - 2026-09-23

- Bump Nextflow 25.10 (#14)
- Use Nextflow Gradle plugin (#15)
- Declare config options with `ConfigScope` (#15)
- Use `TraceObserverV2` (#15)
- Use plugin registry (#15)

Starting in Nextflow 26.10, `weblog.enabled = true` no longer loads the plugin automatically. Add `id 'nf-weblog'` to the `plugins` block.

## [1.1.2] - 2023-11-19

- Bump Nextflow 23.10
- Fix IllegalAccessError by moving classes to `nextflow.weblog` package

## [1.1.1] - 2023-11-08

- Log warnings for exceptions in request thread
- Don't encode username and password for basic auth (#8)

## [1.1.0] - 2023-08-28

- Add trace log on HTTP send
- Add basic token authentication (#3)

## [1.0.0] - 2023-07-18

- Initial release, extracted from Nextflow core
