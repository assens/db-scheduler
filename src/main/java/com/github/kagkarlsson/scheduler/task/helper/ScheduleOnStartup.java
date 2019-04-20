/**
 * Copyright (C) Gustav Karlsson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.kagkarlsson.scheduler.task.helper;

import java.time.Instant;

import com.github.kagkarlsson.scheduler.Scheduler;
import com.github.kagkarlsson.scheduler.task.Task;
import com.google.common.base.Supplier;

class ScheduleOnStartup<T> {
	String instance;
	T data;
	Supplier<Instant> firstExecutionTime;

	ScheduleOnStartup(String instance) {
		this(instance, null);
	}
	
	ScheduleOnStartup(String instance, T data) {
		this(instance, data, () -> Instant.now());
	}
	
	ScheduleOnStartup(String instance, T data, Supplier<Instant> firstExecutionTime) {
		this.firstExecutionTime = firstExecutionTime;
		this.instance = instance;
		this.data = data;
	}

	public void apply(Scheduler scheduler, Task<T> task) {
		if (data == null) {
            scheduler.schedule(task.instance(instance), firstExecutionTime.get());
        } else {
            scheduler.schedule(task.instance(instance, data), firstExecutionTime.get());
        }
	}

}
