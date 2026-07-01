<template>
    <div>
        <h2>Список игр</h2>
        <ul>
            <li v-for="game in games" :key="game.id">
                Игра #{{ game.id }} - статус: {{ game.status }}
            </li>
        </ul>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const games = ref([
    { id: 1, status: 'В процессе' },
    { id: 2, status: 'Завершена'},
    { id: 3, status: 'Ожидание игрока'}
]);
</script>

<style scoped>
</style>