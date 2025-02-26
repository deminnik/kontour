/*
* Copyright 2025 Nikita Demin
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
* */

package engineering.sketches.kontour

object Architecture {
    private val _vertices: MutableSet<Vertex> = mutableSetOf()

    val vertices: Set<Vertex> get() = _vertices.toSet()

    fun include(vararg namespaces: Namespace): Builder {
        for (namespace in namespaces) {
            namespace::class.nestedClasses
                .mapNotNull { it.objectInstance }
                .filterIsInstance<Vertex>()
                .forEach(_vertices::add)
        }

        return Builder()
    }

    class Builder internal constructor() {
        fun build(action: Architecture.() -> Unit) {
            Architecture.action()
        }
    }
}
