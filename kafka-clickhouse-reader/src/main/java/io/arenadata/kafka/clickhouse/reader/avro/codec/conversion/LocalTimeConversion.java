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
package io.arenadata.kafka.clickhouse.reader.avro.codec.conversion;

import io.arenadata.kafka.clickhouse.reader.avro.codec.type.LocalTimeLogicalType;
import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

import java.time.LocalTime;

public class LocalTimeConversion extends Conversion<LocalTime> {

    private LocalTimeConversion() {
        super();
    }

    public static LocalTimeConversion getInstance() {
        return LocalTimeConversion.LocalTimeConversionHolder.INSTANCE;
    }

    @Override
    public Class<LocalTime> getConvertedType() {
        return LocalTime.class;
    }

    @Override
    public String getLogicalTypeName() {
        return LocalTimeLogicalType.INSTANCE.getName();
    }

    @Override
    public Schema getRecommendedSchema() {
        return LocalTimeLogicalType.INSTANCE.addToSchema(Schema.create(Schema.Type.LONG));
    }

    @Override
    public Long toLong(LocalTime value, Schema schema, LogicalType type) {
        return value.toNanoOfDay();
    }

    @Override
    public LocalTime fromLong(Long value, Schema schema, LogicalType type) {
        return LocalTime.ofNanoOfDay(value);
    }

    @Override
    public LocalTime fromCharSequence(CharSequence value, Schema schema, LogicalType type) {
        return LocalTime.parse(value);
    }

    @Override
    public CharSequence toCharSequence(LocalTime value, Schema schema, LogicalType type) {
        return value.toString();
    }

    private static class LocalTimeConversionHolder {
        private static final LocalTimeConversion INSTANCE = new LocalTimeConversion();
    }
}
