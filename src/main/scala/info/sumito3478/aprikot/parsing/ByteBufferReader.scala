/* Copyright (C) 2013 sumito3478 <sumito3478@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package info.sumito3478
package aprikot.parsing

import scala.util.parsing.input._

import java.nio._

class ByteBufferReader(
  val data: ByteBuffer, override val offset: Int) extends Reader[Byte] {
  def this(data: ByteBuffer) = this(data, 0)

  class Position(val offset: Int) extends scala.util.parsing.input.Position {
    override val line = 1

    override def column = offset + 1

    override def lineContents = ""
  }

  override def atEnd = offset >= data.limit

  override def first = data.get(offset)

  override def pos = new Position(offset)

  override def rest = new ByteBufferReader(data, offset + 1)
}