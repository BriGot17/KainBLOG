
import React, { useState } from 'react'
import CKEditor from '@ckeditor/ckeditor5-react'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import { editorConfig } from './configs'

const ArticleEditor = ({ onSubmit }) => {
    const [body, setBody] = useState('')

    ClassicEditor.defaultConfig = editorConfig;
    const handleSubmit = (e) => {
        e.preventDefault()
        onSubmit({ body })
    }

    return (
        <form onSubmit={handleSubmit}>
            <label for="title">Titel</label>
            <input type="text" name="title" />

            <label for="description">Beschreibung (Kurze Zusammenfassung des Inhalts)</label>
            <input type="text" name="description" />
            <CKEditor
                editor={ClassicEditor}
                onChange={(event, editor) => {
                    const data = editor.getData()
                    setBody(data)
                }}
            />
            <button type='submit'>Submit</button>
        </form>
    )
}

export default ArticleEditor