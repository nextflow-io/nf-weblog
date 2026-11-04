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

import nextflow.Session
import spock.lang.Specification

class WebLogFactoryTest extends Specification {

    def 'should create observer only when enabled' () {
        given:
        def session = Mock(Session) {
            getConfig() >> CONFIG
        }

        when:
        def result = new WebLogFactory().create(session)
        then:
        result.size() == SIZE

        where:
        CONFIG                                                  | SIZE
        [:]                                                     | 0
        [weblog: [enabled: false]]                              | 0
        [weblog: [enabled: true]]                               | 1
        [weblog: [enabled: true, url: 'https://foo.com:8080']]  | 1
    }

    def 'should apply config defaults' () {
        expect:
        new WebLogConfig([:]).url == WebLogObserver.DEF_URL
        new WebLogConfig([url: 'http://foo.com']).url == 'http://foo.com'
        !new WebLogConfig([:]).enabled
    }

}
