# Changelog

All notable changes to the nf-weblog plugin will be documented here.

See [Keep a Changelog](http://keepachangelog.com/) for recommendations on how to structure this file.

## [Unreleased]

- Bump Nextflow 25.10 (#14)
- Use Nextflow Gradle plugin
- Declare config options with `ConfigScope`
- Use `TraceObserverV2`
- Use plugin registry

Starting with Nextflow 26.09, `weblog.enabled = true` no longer loads the plugin automatically. Add `id 'nf-weblog'` to the `plugins` block.
