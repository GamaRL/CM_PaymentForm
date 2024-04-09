# Formulario de Pago

> Alumno: Ríos Lira, Gamaliel
>
> Materia: Cómputo Móvil
>
> Semestre: 2024-2

## Consideraciones para el formulario:

Para que los datos sean válidos, se requiere que:
- Número de tarjeta: Debe contener exactamente 16 dígitos decimales
- Válido hasta: Debe tener el formato `mm/YY`. Se valida que la fecha sea posterior al día de la compra (el mismo mes es válido).
- CVV: Se valida que sea un número que tenga tres o cuatros cifras.
- Dueño de la tarjeta: Debe tener al menos un nombre, y puede venir seguido de muchos más nombres y apellidos. **NO se permiten acentos** y el uso de mayúsculas y minúsculas es indistinto.

## Implementación de la cantidad a pagar

Se utilizó la función:

```kotlin
Random.nextInt(100, 5000)
```

a través del cual se tiene un número aleatorio en el intervalo 100 y 5000 (tal como se solicita en las especificaciones)

## Implementación del éxito/fallo de la operación

Se utilizó la siguiente idea:

```kotlin
if (Random.nextInt(1, 4) == 1) {
    // Fallo
} else {
    // Éxito
}
```

Como solo existe una posibilidad de éxito (cuando `Random.nextInt()` devuelve `1`) y se tienen cuatro posibles resultados (`1`, `2`, `3` y `4`), entonces, la probabilidad de fallo es del `25%`, mientras que la probabilidad de éxito es del `75%` restante.
