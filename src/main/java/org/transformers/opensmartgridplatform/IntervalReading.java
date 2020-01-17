/* Copyright (c) 2020, RTE (http://www.rte-france.com)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */



package org.transformers.opensmartgridplatform;

public class IntervalReading {

    public IntervalReading(String value, long timeStamp) {
        this.value = value;
        this.timeStamp = timeStamp;
    }

    public String value;

    /** the dateTime as milliseconds from the epoch */ //TODO Check if milli or seconds
    public long timeStamp;

}
