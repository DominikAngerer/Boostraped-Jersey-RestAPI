package at.adtime.gson.provider.v1.util;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonUtil {
	public static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXX";

	private static Gson gson;
	private static Gson gsonExpose;
	private static SimpleDateFormat sdf;

	public static Gson getInstance() {
		if (gson == null) {
			gson = getGsonBuilderInstance(false).create();
		}
		return gson;
	}

	public static Gson getExposeInstance() {
		if (gsonExpose == null) {
			gsonExpose = getGsonBuilderInstance(true).create();
		}
		return gsonExpose;
	}

	public static Gson getInstance(boolean onlyExpose) {
		if (!onlyExpose) {
			if (gson == null) {
				gson = getGsonBuilderInstance(false).create();
			}
			return gson;
		} else {
			if (gsonExpose == null) {
				gsonExpose = getGsonBuilderInstance(true).create();
			}
			return gsonExpose;
		}
	}

	public static SimpleDateFormat getSDFInstance() {
		if (sdf == null) {
			sdf = new SimpleDateFormat(PATTERN);
		}
		return sdf;
	}

	private static GsonBuilder getGsonBuilderInstance(boolean onlyExpose) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		if (onlyExpose) {
			gsonBuilder.excludeFieldsWithoutExposeAnnotation();
		}
		gsonBuilder.registerTypeAdapter(Date.class,
				new JsonDeserializer<Date>() {
					@Override
					public Date deserialize(JsonElement json, Type type,
							JsonDeserializationContext arg2)
							throws JsonParseException {
						try {
							return getSDFInstance().parse(json.getAsString());
						} catch (ParseException e) {
							return null;
						}
					}

				});
		gsonBuilder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
			@Override
			public JsonElement serialize(Date src, Type typeOfSrc,
					JsonSerializationContext context) {
				return src == null ? null : new JsonPrimitive(getSDFInstance()
						.format(src));
			}
		});
		return gsonBuilder;
	}

	public static <T> T fromJson(String json, Class<T> classOfT,
			boolean onlyExpose) {
		try {
			return getInstance(onlyExpose).fromJson(json, classOfT);
		} catch (Exception ex) {
			// Log exception
			return null;
		}
	}
}
