/*
 * Copyright 2021, Seqera Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nextflow.weblog

import groovy.transform.CompileStatic
import nextflow.config.spec.ConfigOption
import nextflow.config.spec.ConfigScope
import nextflow.config.spec.ScopeName
import nextflow.script.dsl.Description

@ScopeName('weblog')
@Description('''
    The `weblog` scope allows you to configure the `nf-weblog` plugin.
''')
@CompileStatic
class WebLogConfig implements ConfigScope {

    @ConfigOption
    @Description('''
        When `true`, send HTTP POST requests to the configured URL (default: `false`).
    ''')
    final boolean enabled

    @ConfigOption
    @Description('''
        The URL where HTTP POST requests are sent (default: `http://localhost`).
    ''')
    final String url

    @ConfigOption
    @Description('''
        Basic authentication credentials in the form `USER:PASSWORD`.
    ''')
    final String basicToken

    /* required by extension point -- do not remove */
    WebLogConfig() {}

    WebLogConfig(Map opts) {
        enabled = opts.enabled as boolean
        url = opts.url ?: WebLogObserver.DEF_URL
        basicToken = opts.basicToken
    }
}
