package code.model;



import org.bson.types.ObjectId
import org.joda.time.DateTime

import net.liftweb._
import common._
import http.{StringField => _, BooleanField => _, _}
import mongodb.record.field._
import record.field._
import util.FieldContainer
import net.liftweb.mongodb.record.MongoRecord
import net.liftmodules.mongoauth.ProtoAuthUserMeta
import code.lib.RogueMetaRecord
import com.foursquare.rogue.LatLong


class project extends MongoRecord[project] with ObjectIdPk[project]{
  def meta = project
  object title extends StringField(this, 70)
  object active extends BooleanField(this){
    override def defaultValue = true
  }
  object description extends StringField(this, 200)
  object description_en extends StringField(this, 200)

  object address extends StringField(this, 200)
  object geolatlng extends MongoCaseClassField[project, LatLong](this) { override def name = "latlng" }




  def projectScreenFields = new FieldContainer {
    def allFields = List(title, description, description_en)
  }

}

object project extends project with RogueMetaRecord[project]{
  import mongodb.BsonDSL._

  ensureIndex(geolatlng.name -> "2d", unique=false)


}
