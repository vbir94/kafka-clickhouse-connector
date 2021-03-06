/*
 * Copyright © 2021 Kafka Clickhouse Reader
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.arenadata.kafka.clickhouse.reader.converter.impl;

import io.arenadata.kafka.clickhouse.reader.converter.transformer.AbstractColumnTransformer;
import io.arenadata.kafka.clickhouse.reader.model.ColumnType;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class UuidFromStringTransformer extends AbstractColumnTransformer<UUID, String> {

    @Override
    public UUID transformValue(String value) {
        return value == null ? null : UUID.fromString(value);
    }

    @Override
    public Collection<Class<?>> getTransformClasses() {
        return Collections.singletonList(String.class);
    }

    @Override
    public ColumnType getType() {
        return ColumnType.UUID;
    }
}
