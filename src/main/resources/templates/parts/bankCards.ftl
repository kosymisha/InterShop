
    <tr class="rows">
        <td scope="row">ends at ${activeCard.getLastNumbers()}</td>
        <td>${activeCard.firstNameCard} ${activeCard.lastNameCard}</td>
        <td>${activeCard.month}/${activeCard.year}</td>
        <td>
            DEFAULT
        </td>
        <td>

        </td>
    </tr>
<#list nonActiveCards as card>
    <tr class="rows">
        <td scope="row">ends at ${card.getLastNumbers()}</td>
        <td>${card.firstNameCard} ${card.lastNameCard}</td>
        <td>${card.month}/${card.year}</td>
        <td>
            <button class="btn btn-secondary" onclick="makeDefault('${card.getId()}')">MAKE DEFAULT</button>
        </td>
        <td>
            <button class="btn btn-danger" onclick="deleteBankCard('${card.getId()}')" >DELETE</button>
        </td>
    </tr>
</#list>