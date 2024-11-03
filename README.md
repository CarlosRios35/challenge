# challenge Java

El objetivo de este servicio es disponibilizar un punto de acceso Rest para poder realizar
consultas de precios sobre productos.

# La firma del servicio es la siguiente:
  http://{url}:{port}/v1/{country}/products/{product_id}/brand/{brand_id}?date={fecha}

En el proyecto vamos a encontrar un file pricesProducts.yaml con la especificación del api.

Ejemplo de consumo desde postman:
Se deja el puerto por default y en caso de correrlo local se deja el curl adjunto.

  curl --location 'http://localhost:8080/v1/es/products/35455/brand/1?date=2020-06-14-10.00.00' \
--header 'Cookie: JSESSIONID=DC63D647D852C8EA016E40D3360ABE18'

# Especificación del desarrollo

* Para el desarrollo se hizo foco sobre la arquitectura exagonal o mejor nombrada - port and adapters -
en donde se crearon los modulos application, domain y intrastructure por lo que se hace una separación
clara de capas y se evita el alto acoplamiento.

* Se usa una base de datos H2 y se popula durante el inicio.

* Por seguridad se define un interceptor para validar los parametros de entrada, y se retorna un error
generico sin dar mayor detalle sobre el error para evitar dar información en caso que se ataque el api.

* Se realiza una consulta eficiente a la base de tal modo que retorne solo un registro, dado que el
consumo a la base es costoso en un ambiente de alta concurrencia, se puede optimizar mas por ejemplo
usando una cache dado que los precios no se van a mover todo el tiempo de esa forma evitariamos ir 
todo el tiempo a la base de datos, para este ejemplo no es necesario ya que se uso un H2 pero en caso 
de una base de datos seria inevitable una cache.

* Otro punto que no es menor es la configuración de open telemetry, que tambien es escencial para un ambiente 
productivo de alta concurrencia, para este ejemplo no lo vi necesario pero se deja como observación.

* Se hizo el desarrollo con los test respectivos,dando cobertura al codigo para asegurarnos que el
  desarrollo se comporte como esperamos, esto ultimo es escencial porque cuando se mueve mucho son
  los test que nos ayudan a realizar un desarrollo en un ambiente controlado.




