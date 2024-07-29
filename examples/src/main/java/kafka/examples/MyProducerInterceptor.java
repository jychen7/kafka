/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kafka.examples;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MyProducerInterceptor implements ProducerInterceptor<Integer, String> {

    @Override
    public ProducerRecord<Integer, String> onSend(ProducerRecord<Integer, String> record) {
        ProducerRecord<Integer, String> newRecord = new ProducerRecord<>(record.topic(), record.key(), record.value());
        newRecord.headers().add("from_interceptor", "value".getBytes(StandardCharsets.UTF_8));
        return newRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
    }

    @Override
    public void close() {
        // Cleanup if needed
    }

    @Override
    public void configure(Map<String, ?> configs) {
        // Configure interceptor if needed
    }
}