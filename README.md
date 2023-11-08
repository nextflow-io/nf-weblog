# nf-weblog plugin 
   
This plugin allows Nextflow to send detailed workflow execution metadata and runtime statistics to a HTTP endpoint. 
To enable this feature, use the `-with-weblog` as shown below:

```bash
nextflow run <pipeline name> -with-weblog [url]
```

Or enable it in the Nextflow configuration:

```groovy
weblog {
  enabled = true
  url = '...'
}
```

Workflow events are sent as HTTP POST requests to the given URL. The message consists of the following JSON structure:

```json
{
  "runName": "<run name>",
  "runId": "<uuid>",
  "event": "<started|process_submitted|process_started|process_completed|error|completed>",
  "utcTime": "<UTC timestamp>",
  "trace": {  },
  "metadata": {  }
}
```

The JSON object contains the following attributes:

- `runName`: The workflow execution run name.

- `runId`: The workflow execution unique ID.

- `event`: The workflow execution event. One of `started`, `process_submitted`, `process_started`, `process_completed`, `error`, `completed`.

- `utcTime`: The UTC timestamp in ISO 8601 format.

- `trace`: The task runtime information as described in the [Trace report](https://nextflow.io/docs/latest/tracing.html#trace-report) documentation. The set of included fields is determined by the `trace.fields` setting in the Nextflow configuration file. See the [Trace configuration](https://nextflow.io/docs/latest/config.html#scope-trace) docs to learn more.

  *Included only for the following events: `process_submitted`, `process_started`, `process_completed`, `error`*

- `metadata`: The workflow metadata including the [config manifest](https://nextflow.io/docs/latest/config.html#scope-manifest). For a list of all fields, have a look at the bottom message examples.

  *Included only for the following events: `started`, `completed`*

## Configuration

The following configuration options are available:

`weblog.enabled`

If `true` it will send HTTP POST requests to a given url.

`weblog.url`

The url where to send HTTP POST requests (default: `http://localhost`).

`weblog.basicToken`

A Basic authentication token to append to each HTTP request. It should be the user ID and password joined by a single colon, for example `USER:PASSWORD`.

## Examples

### Example `started` event

When a workflow execution is started, a message like the following is posted to the specified end-point. Be aware that the properties in the parameter scope will look different for your workflow. Here is an example output from the `nf-core/hlatyping` pipeline with the weblog feature enabled:

```json
{
  "runName": "friendly_pesquet",
  "runId": "170aa09c-105f-49d0-99b4-8eb6a146e4a7",
  "event": "started",
  "utcTime": "2018-10-07T11:42:08Z",
  "metadata": {
    "params": {
      "container": "nfcore/hlatyping:1.1.4",
      "help": false,
      "outdir": "results",
      "bam": true,
      "singleEnd": false,
      "single-end": false,
      "reads": "data/test*{1,2}.fq.gz",
      "seqtype": "dna",
      "solver": "glpk",
      "igenomes_base": "./iGenomes",
      "multiqc_config": "/Users/sven1103/.nextflow/assets/nf-core/hlatyping/conf/multiqc_config.yaml",
      "clusterOptions": false,
      "cluster-options": false,
      "enumerations": 1,
      "beta": 0.009,
      "prefix": "hla_run",
      "base_index": "/Users/sven1103/.nextflow/assets/nf-core/hlatyping/data/indices/yara/hla_reference_",
      "index": "/Users/sven1103/.nextflow/assets/nf-core/hlatyping/data/indices/yara/hla_reference_dna",
      "custom_config_version": "master",
      "custom_config_base": "https://raw.githubusercontent.com/nf-core/configs/master"
    },
    "workflow": {
      "start": "2019-03-25T12:09:52Z",
      "projectDir": "/Users/sven1103/.nextflow/assets/nf-core/hlatyping",
      "manifest": {
        "nextflowVersion": ">=18.10.1",
        "defaultBranch": "master",
        "version": "1.1.4",
        "homePage": "https://github.com/nf-core/hlatyping",
        "gitmodules": null,
        "description": "Precision HLA typing from next-generation sequencing data.",
        "name": "nf-core/hlatyping",
        "mainScript": "main.nf",
        "author": null
      },
      "complete": null,
      "profile": "docker,test",
      "homeDir": "/Users/sven1103",
      "workDir": "/Users/sven1103/git/nextflow/work",
      "container": "nfcore/hlatyping:1.1.4",
      "commitId": "4bcced898ee23600bd8c249ff085f8f88db90e7c",
      "errorMessage": null,
      "repository": "https://github.com/nf-core/hlatyping.git",
      "containerEngine": "docker",
      "scriptFile": "/Users/sven1103/.nextflow/assets/nf-core/hlatyping/main.nf",
      "userName": "sven1103",
      "launchDir": "/Users/sven1103/git/nextflow",
      "runName": "shrivelled_cantor",
      "configFiles": [
        "/Users/sven1103/.nextflow/assets/nf-core/hlatyping/nextflow.config"
      ],
      "sessionId": "7f344978-999c-480d-8439-741bc7520f6a",
      "errorReport": null,
      "scriptId": "2902f5aa7f297f2dccd6baebac7730a2",
      "revision": "master",
      "exitStatus": null,
      "commandLine": "./launch.sh run nf-core/hlatyping -profile docker,test -with-weblog 'http://localhost:4567'",
      "nextflow": {
        "version": "19.03.0-edge",
        "build": 5137,
        "timestamp": "2019-03-28T14:46:55Z"
      }
    },
    "stats": {
      "computeTimeFmt": "(a few seconds)",
      "cachedCount": 0,
      "cachedDuration": 0,
      "failedDuration": 0,
      "succeedDuration": 0,
      "failedCount": 0,
      "cachedPct": 0.0,
      "cachedCountFmt": "0",
      "succeedCountFmt": "0",
      "failedPct": 0.0,
      "failedCountFmt": "0",
      "ignoredCountFmt": "0",
      "ignoredCount": 0,
      "succeedPct": 0.0,
      "succeedCount": 0,
      "ignoredPct": 0.0
    },
    "resume": false,
    "success": false,
    "scriptName": "main.nf",
    "duration": null
  }
}
```

### Example `completed` event

When a task is completed, a message like the following is posted to the specified end-point:

```json
{
  "runName": "friendly_pesquet",
  "runId": "170aa09c-105f-49d0-99b4-8eb6a146e4a7",
  "event": "process_completed",
  "utcTime": "2018-10-07T11:45:30Z",
  "trace": {
    "task_id": 2,
    "status": "COMPLETED",
    "hash": "a1/0024fd",
    "name": "make_ot_config",
    "exit": 0,
    "submit": 1538912529498,
    "start": 1538912529629,
    "process": "make_ot_config",
    "tag": null,
    "module": [

    ],
    "container": "nfcore/hlatyping:1.1.1",
    "attempt": 1,
    "script": "\n    configbuilder --max-cpus 2 --solver glpk > config.ini\n    ",
    "scratch": null,
    "workdir": "/home/sven1103/git/hlatyping-workflow/work/a1/0024fd028375e2b601aaed44d112e3",
    "queue": null,
    "cpus": 1,
    "memory": 7516192768,
    "disk": null,
    "time": 7200000,
    "env": "PATH=/home/sven1103/git/hlatyping-workflow/bin:$PATH\n",
    "error_action": null,
    "complete": 1538912730599,
    "duration": 201101,
    "realtime": 69,
    "%cpu": 0.0,
    "%mem": 0.1,
    "vmem": 54259712,
    "rss": 10469376,
    "peak_vmem": 20185088,
    "peak_rss": 574972928,
    "rchar": 7597,
    "wchar": 162,
    "syscr": 16,
    "syscw": 4083712,
    "read_bytes": 4096,
    "write_bytes": 0,
    "native_id": 27185
  }
}
```


## Testing and debugging

To build and test the plugin during development, configure a local Nextflow build with the following steps:

1. Clone the Nextflow repository in your computer into a sibling directory:
    ```bash
    git clone --depth 1 https://github.com/nextflow-io/nextflow ../nextflow
    ```
  
2. Configure the plugin build to use the local Nextflow code:
    ```bash
    echo "includeBuild('../nextflow')" >> settings.gradle
    ```
  
   (Make sure to not add it more than once!)

3. Compile the plugin alongside the Nextflow code:
    ```bash
    make compile
    ```

4. Run Nextflow with the plugin, using `./launch.sh` as a drop-in replacement for the `nextflow` command, and adding the option `-plugins nf-weblog` to load the plugin:
    ```bash
    ./launch.sh run nextflow-io/hello -plugins nf-weblog
    ```

### Test endpoint

The `echo.py` script can be used to deploy a local server that logs POST requests, which is useful for testing the plugin:

```bash
./echo.py
```

## Testing without Nextflow build

The plugin can be tested without using a local Nextflow build using the following steps:

1. Build the plugin: `make buildPlugins`
2. Copy `build/plugins/<your-plugin>` to `$HOME/.nextflow/plugins`
3. Create a pipeline that uses your plugin and run it: `nextflow run ./my-pipeline-script.nf`

## Package, upload, and publish

The project should be hosted in a GitHub repository whose name matches the name of the plugin, that is the name of the directory in the `plugins` folder (e.g. `nf-weblog`).

Follow these steps to package, upload and publish the plugin:

1. Create a file named `gradle.properties` in the project root containing the following attributes (this file should not be committed to Git):

   * `github_organization`: the GitHub organisation where the plugin repository is hosted.
   * `github_username`: The GitHub username granting access to the plugin repository.
   * `github_access_token`: The GitHub access token required to upload and commit changes to the plugin repository.
   * `github_commit_email`: The email address associated with your GitHub account.

2. Use the following command to package and create a release for your plugin on GitHub:
    ```bash
    ./gradlew :plugins:nf-weblog:upload
    ```

3. Create a pull request against [nextflow-io/plugins](https://github.com/nextflow-io/plugins/blob/main/plugins.json) to make the plugin accessible to Nextflow.
