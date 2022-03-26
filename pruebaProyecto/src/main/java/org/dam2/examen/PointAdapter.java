package org.dam2.examen;

import java.io.IOException;

import org.springframework.data.geo.Point;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


public class PointAdapter extends TypeAdapter<Point>{
	

	@Override
	public void write(JsonWriter out, Point value) throws IOException {
		/*
		Gson gson = new Gson();
		
		out.beginObject();
		out.name("type");
		out.jsonValue("Point");
		out.name("coordinates");
		out.beginArray();
		out.jsonValue(value.getX() + "");
		out.jsonValue(value.getY() + "");
		out.endArray();
		out.endObject();
		
		
		/*
		// TODO Auto-generated method stub
		Gson gson = new Gson ();

		
		// escribrir inicio objeto
		out.beginObject();
		
		// escribir clave localidad
		out.name("localidad");
		
		
		if (value.getLocalidad().size() > 1) // escribir array
		{
			out.beginArray();
			for (Localidad l : value.getLocalidad())
				out.jsonValue(gson.toJson(l));
			out.endArray();
		}
		else // escribir un sólo elemento
			out.jsonValue(gson.toJson(value.getLocalidad().get(0)));
		
		
		
		// escribir fin del objeto
		out.endObject();
		*/
		
	}

	@Override
	public Point read(JsonReader in) throws IOException {
		/*
		// TODO Auto-generated method stub
		Gson gson = new Gson ();
		
		/*
		in.beginObject();
		in.
        gen.writeStringField("type", value.getType());
        gen.writeArrayFieldStart("coordinates");
        gen.writeObject(value.getCoordinates());
        gen.writeEndArray();
        gen.writeEndObject();
		*/
		
		/*
		Localidades localidades = new Localidades ();
		Gson gson = new Gson ();
		
		//System.out.println(in.peek());
		// Consumir comienzo objeto {
		in.beginObject();
		//System.out.println(in.peek());
		// Consumir nombre localidad
		in.nextName();
		//System.out.println(in.peek());
		
		if (in.peek() == JsonToken.BEGIN_ARRAY) // si llega un array
		{
			// Consumir array
			Type  listaDeLocaliades = TypeToken.getParameterized(ArrayList.class, Localidad.class).getType();
			localidades.setLocalidad(gson.fromJson(in, listaDeLocaliades));
			
		}

		else //llega un elemento consumirlo
			localidades.getLocalidad().add(gson.fromJson(in, Localidad.class));
		
		
		// Consumir final objeto 
		in.endObject();
		
		return localidades;
		
		*/
		System.err.println("soflnrslkgn");
		return new Point (9,9);
	}

}
